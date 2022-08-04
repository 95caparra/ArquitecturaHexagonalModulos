package co.com.movii.db;

import co.com.movii.model.exceptions.OtpException;
import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.DeviceValidationAttemp;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;
import co.com.movii.ports.in.OtpInPort;
import co.com.movii.model.OtpRequest;
import co.com.movii.ports.out.ValidateDeviceOtpOutPort;
import co.com.movii.ports.out.ValidateDeviceOutPort;
import co.com.movii.repository.*;
import co.com.movii.utils.ConverObject;
import co.com.movii.utils.Hash;
import co.com.movii.mapper.DeviceInformationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import co.com.movii.ports.in.RegistryDeviceInPort;
import co.com.movii.entities.*;
import co.com.movii.model.session.SessionOTP;

@Component
@AllArgsConstructor
public class ValidateDbConnector implements ValidateDeviceOutPort, ValidateDeviceOtpOutPort {

    private final AccountRepository accountRepository;

    private final WhiteListRepository whiteListRepository;

    private final BlackListRepository blackListRepository;

    private final DeviceValidationAttempRepository deviceValidationAttempRepository;

    private final OtpInPort otpInPort;
    private final RegistryDeviceInPort deviceInPort;

    private final DeviceInformationRepository deviceInformationRepository;
    private final DeviceRepository deviceRepository;

    @Override
    public ResponseDevice validateDevice(DeviceInformation deviceInformation, String phoneNumber) throws DeviceValidatorException{

        ResponseDevice response = ResponseDevice.builder()
                .responseCode("200")
                .responseMessage("Device is Valid")
                .build();
        try {
            String hash = generateHash(deviceInformation);

            //consulta black_list y white_list
            BlackListEntity blackListEntity = blackListRepository.findByRegistryDeviceIdAndDeviceHash(deviceInformation.getDeviceId(), hash);

            WhiteListEntity whiteListEntity = whiteListRepository.findByRegistryDeviceIdAndDeviceHash(deviceInformation.getDeviceId(), hash);

            if (whiteListEntity != null){
                // dispositivo valido seguir
            } else if (blackListEntity != null){
                // salir, dispositivo no valido
                registryAttemp("",deviceInformation.getDeviceId(), "", "FAIL BLACK LIST");
                response = ResponseDevice.builder()
                        .responseCode("500")
                        .responseMessage("Invalid Device BlackList")
                        .build();
            } else {
                AccountEntity accountEntity = accountRepository.findByPhoneNumberOrderByIdAccountEntityDesc(phoneNumber);
                if (accountEntity == null){
                    // registrar dispositivo
                    ResponseDevice responseDeviceRegistry = registry(deviceInformation, phoneNumber);
                    if(responseDeviceRegistry.getResponseCode().equalsIgnoreCase("200")){
                        // Enviar OTP
                        OtpRequest request = requestOtp(phoneNumber, "");
                        SessionOTP sessionOTP = generateOtp(request);

                        if(sessionOTP != null){
                            response = ResponseDevice.builder()
                                    .responseCode("200")
                                    .responseMessage("Registry Device, Send OTP")
                                    .build();
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new DeviceValidatorException(e);
        }
        return response;
    }

    @Override
    public ResponseDevice validateDeviceOtp(DeviceInformation deviceInformation, String phoneNumber, String otp) throws DeviceValidatorException {

        ResponseDevice response = ResponseDevice.builder()
                .responseCode("200")
                .responseMessage("Device is Valid")
                .build();

        try {
            String hash = generateHash(deviceInformation);

            //consulta black_list y white_list
            BlackListEntity blackListEntity = blackListRepository.findByRegistryDeviceIdAndDeviceHash(deviceInformation.getDeviceId(), hash);

            WhiteListEntity whiteListEntity = whiteListRepository.findByRegistryDeviceIdAndDeviceHash(deviceInformation.getDeviceId(), hash);

            if (whiteListEntity != null){
                // dispositivo valido seguir
            } else if (blackListEntity != null){
                // salir, dispositivo no valido
                registryAttemp("",deviceInformation.getDeviceId(), "", "FAIL BLACK LIST");
                response = ResponseDevice.builder()
                        .responseCode("500")
                        .responseMessage("Invalid Device BlackList")
                        .build();
            } else {
                AccountEntity account = accountRepository.findByPhoneNumberOrderByIdAccountEntityDesc(phoneNumber);
                DeviceEntity device =  deviceRepository.findByDeviceIdOrderByIdDeviceEntity(deviceInformation.getDeviceId());

                if (!account.getIsValidated() && !device.getIsValidated()){
                    // Enviar OTP para validar
                    OtpRequest request = requestOtp(phoneNumber, otp);
                    SessionOTP sessionOTP = otpInPort.validateOtp(request);

                    if (sessionOTP != null &&
                            sessionOTP.getResponseCode().equalsIgnoreCase("00")){

                            device = deviceRepository.findByIdDeviceEntity(device.getIdDeviceEntity());
                            account = accountRepository.findByIdAccountEntity(account.getIdAccountEntity());

                            account.setIsValidated(true);
                            device.setIsValidated(true);

                            accountRepository.save(account);
                            deviceRepository.save(device);
                        }
                    }
                }
        } catch (OtpException e) {
            response = ResponseDevice.builder()
                    .responseCode("500")
                    .responseMessage("Otp is invalid")
                    .build();
        } catch (Exception e) {
            throw new DeviceValidatorException(e);
        }
        return response;
    }

    public String generateHash(DeviceInformation deviceInformation) throws Exception {
        String string = ConverObject.objectToString(deviceInformation);
        return Hash.getSHA256Hash(string);
    }

    public void registryAttemp(String registryDeviceId, String deviceId, String accountId, String status) throws Exception {
        String string = ConverObject.objectToString(DeviceValidationAttemp.builder()
                .registryDeviceId(registryDeviceId)
                .deviceId(deviceId)
                .accountId(accountId)
                .validationStatus(status)
                .isAltered(false).build());
        String hash =  Hash.getSHA256Hash(string);

        DeviceValidationAttemp attemp = DeviceValidationAttemp.builder()
                .registryDeviceId(registryDeviceId)
                .deviceId(deviceId)
                .accountId(accountId)
                .validationStatus(status)
                .signature(hash)
                .isAltered(false)
                .build();

        DeviceValidationAttempEntity attempEntity = DeviceInformationMapper.INSTANCE.deviceValidationAttempToDeviceValidationAttempEntity(attemp);

        deviceValidationAttempRepository.save(attempEntity);
    }

    public OtpRequest requestOtp(String phoneNumber, String otp) {
        return OtpRequest.builder()
                .email("")
                .phoneNumber(phoneNumber)
                .otp(otp)
                .build();
    }

    public ResponseDevice registry(DeviceInformation deviceInformation, String phoneNumber) throws DeviceValidatorException {
        return deviceInPort.registryDevice(deviceInformation, phoneNumber);
    }

    public SessionOTP generateOtp(OtpRequest request) throws OtpException {
        return otpInPort.generateOtp(request);
    }









}
