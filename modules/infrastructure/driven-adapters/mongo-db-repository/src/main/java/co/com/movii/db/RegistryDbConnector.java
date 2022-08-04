package co.com.movii.db;

import co.com.movii.entities.AccountEntity;
import co.com.movii.entities.DeviceEntity;
import co.com.movii.entities.DeviceInformationEntity;
import co.com.movii.mapper.DeviceInformationMapper;
import co.com.movii.model.device.Account;
import co.com.movii.model.device.Device;
import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;
import co.com.movii.ports.out.RegistryDeviceOutPort;
import co.com.movii.repository.AccountRepository;
import co.com.movii.repository.DeviceInformationRepository;
import co.com.movii.repository.DeviceRepository;
import co.com.movii.utils.ConverObject;
import co.com.movii.utils.Hash;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class RegistryDbConnector implements RegistryDeviceOutPort {

    private final DeviceInformationRepository deviceInformationRepository;

    private final DeviceRepository deviceRepository;

    private final AccountRepository accountRepository;

    @Override
    public ResponseDevice registryDevice(DeviceInformation deviceInformation, String phoneNumber) throws DeviceValidatorException {

        String responseCode = "200";
        String responseMessage = "Device registry";

        try {
            DeviceInformationEntity deviceInformationEntity = DeviceInformationMapper.INSTANCE.deviceInformationToDeviceInformationEntity(deviceInformation);

            deviceInformationRepository.save(deviceInformationEntity);

            String deviceHashInformation = generateHashDeviceInformation(deviceInformation);

            String deviceHash = generateHashDevice(Device.builder()
                    .deviceHash(deviceHashInformation)
                    .registrationDateTime(String.valueOf(LocalDateTime.now()))
                    .updateDateTime(String.valueOf(LocalDateTime.now()))
                    .deviceId(deviceInformation.getDeviceId())
                    .isValidated(false)
                    .deviceInformation(deviceInformation)
                    .isAltered(false).build());

            Device device = Device.builder()
                    .deviceHash(deviceHashInformation)
                    .registrationDateTime(String.valueOf(LocalDateTime.now()))
                    .updateDateTime(String.valueOf(LocalDateTime.now()))
                    .deviceId(deviceInformation.getDeviceId())
                    .isValidated(false)
                    .deviceInformation(deviceInformation)
                    .signature(deviceHash)
                    .isAltered(false)
                    .build();

            DeviceEntity deviceEntity = DeviceInformationMapper.INSTANCE.deviceToDeviceEntity(device);

            deviceRepository.save(deviceEntity);

            String accountHash = generateHashAccount(Account.builder()
                    .phoneNumber(phoneNumber)
                    .registryDeviceId(deviceEntity.getIdDeviceEntity())
                    .isValidated(false)
                    .isActive(false)
                    .registrationDateTime(String.valueOf(LocalDateTime.now()))
                    .isAltered(false).build());

            Account account = Account.builder()
                    .phoneNumber(phoneNumber)
                    .registryDeviceId(deviceEntity.getIdDeviceEntity())
                    .isValidated(false)
                    .isActive(false)
                    .registrationDateTime(String.valueOf(LocalDateTime.now()))
                    .signature(accountHash)
                    .isAltered(false)
                    .build();

            AccountEntity accountEntity = DeviceInformationMapper.INSTANCE.accountToAccountEntity(account);

            accountRepository.save(accountEntity);


        } catch (Exception e) {
            throw new DeviceValidatorException(e);
        }

        return  ResponseDevice.builder()
                .responseCode(responseCode)
                .responseMessage(responseMessage)
                .build();


    }

    public String generateHashDevice(Device device) throws Exception {
        String string = ConverObject.objectToString(device);
        return Hash.getSHA256Hash(string);
    }

    public String generateHashAccount(Account account) throws Exception {
        String string = ConverObject.objectToString(account);
        return Hash.getSHA256Hash(string);
    }

    public String generateHashDeviceInformation(DeviceInformation deviceInformation) throws Exception {
        String string = ConverObject.objectToString(deviceInformation);
        return Hash.getSHA256Hash(string);
    }
}
