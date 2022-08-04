package co.com.movii.db;

import co.com.movii.entities.*;
import co.com.movii.model.OtpRequest;
import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;
import co.com.movii.model.exceptions.OtpException;
import co.com.movii.model.session.SessionOTP;
import co.com.movii.ports.in.OtpInPort;
import co.com.movii.ports.in.RegistryDeviceInPort;
import co.com.movii.repository.*;
import co.com.movii.utils.ConverObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ValidateDbConnectorTest {

    @InjectMocks
    ValidateDbConnector validateDbConnector;

    @Mock
    WhiteListRepository whiteListRepository;

    @Mock
    BlackListRepository blackListRepository;

    @Mock
    AccountRepository accountRepository;

    @Mock
    DeviceRepository deviceRepository;

    @Mock
    RegistryDeviceInPort registryDeviceInPort;

    @Mock
    OtpInPort otpInPort;

    @Mock
    DeviceValidationAttempRepository deviceValidationAttempRepository;

    @Mock
    ConverObject converObject;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateDeviceTest() throws Exception {
        //  Arrange

        DeviceInformation deviceInformation = DeviceInformation.builder().build();



        Mockito.when(blackListRepository.findByRegistryDeviceIdAndDeviceHash(Mockito.any(), Mockito.any())).thenReturn(null);
        Mockito.when(whiteListRepository.findByRegistryDeviceIdAndDeviceHash(Mockito.any(), Mockito.any())).thenReturn(null);

        AccountEntity accountEntity = AccountEntity.builder()
                .isValidated(false).build();

        Mockito.when(accountRepository.findByPhoneNumberOrderByIdAccountEntityDesc(Mockito.any())).thenReturn(null);

        DeviceEntity deviceEntity = DeviceEntity.builder()
                .isValidated(false).build();

        Mockito.when(deviceRepository.findByDeviceIdOrderByIdDeviceEntity(Mockito.any())).thenReturn(deviceEntity);

        DeviceEntity device = DeviceEntity.builder().build();
        Mockito.when(deviceRepository.findByIdDeviceEntity(Mockito.any())).thenReturn(device);

        AccountEntity account = AccountEntity.builder().build();
        Mockito.when(accountRepository.findByIdAccountEntity(Mockito.any())).thenReturn(account);

        ResponseDevice responseDeviceRegistry = ResponseDevice.builder()
                .responseCode("200")
                .build();
        Mockito.when(registryDeviceInPort.registryDevice(deviceInformation, "31234321122")).thenReturn(responseDeviceRegistry);


        SessionOTP sessionOTP = SessionOTP.builder()
                .responseCode("00")
                .responseMessage("success")
                .otp("123")
                .build();

        Mockito.when(otpInPort.generateOtp(Mockito.any())).thenReturn(sessionOTP);

        //Act
        ResponseDevice responseDevice = validateDbConnector.validateDevice(deviceInformation, "31234321122");

        //Assert
        Assertions.assertNotNull(responseDevice);
    }

    @Test
    void validateDeviceCatchTest() throws Exception {
        //  Arrange

        DeviceInformation deviceInformation = DeviceInformation.builder().build();



        Mockito.when(blackListRepository.findByRegistryDeviceIdAndDeviceHash(Mockito.any(), Mockito.any())).thenReturn(null);
        Mockito.when(whiteListRepository.findByRegistryDeviceIdAndDeviceHash(Mockito.any(), Mockito.any())).thenReturn(null);

        AccountEntity accountEntity = AccountEntity.builder()
                .isValidated(false).build();

        Mockito.when(accountRepository.findByPhoneNumberOrderByIdAccountEntityDesc(Mockito.any())).thenReturn(null);

        DeviceEntity deviceEntity = DeviceEntity.builder()
                .isValidated(false).build();

        Mockito.when(deviceRepository.findByDeviceIdOrderByIdDeviceEntity(Mockito.any())).thenReturn(deviceEntity);

        DeviceEntity device = DeviceEntity.builder().build();
        Mockito.when(deviceRepository.findByIdDeviceEntity(Mockito.any())).thenReturn(device);

        AccountEntity account = AccountEntity.builder().build();
        Mockito.when(accountRepository.findByIdAccountEntity(Mockito.any())).thenReturn(account);

        ResponseDevice responseDeviceRegistry = ResponseDevice.builder()
                .responseCode("200")
                .build();
        Mockito.when(registryDeviceInPort.registryDevice(deviceInformation, "31234321122")).thenReturn(responseDeviceRegistry);


        SessionOTP sessionOTP = SessionOTP.builder()
                .responseCode("00")
                .responseMessage("success")
                .otp("123")
                .build();

        Mockito.when(otpInPort.generateOtp(Mockito.any())).thenReturn(null);

        Mockito.when(validateDbConnector.validateDevice(deviceInformation, "31234321122")).thenThrow(OtpException.class);
        //Act Y Assert
        DeviceValidatorException optException = Assertions.assertThrows(DeviceValidatorException.class, () -> {
                    validateDbConnector.validateDevice(deviceInformation, "31234321122");
                }, "DeviceValidatorException error was expected");

        Assertions.assertTrue(optException instanceof  DeviceValidatorException);
    }

    @Test
    void validateDeviceBlackListTest() throws Exception {
        //  Arrange

        DeviceInformation deviceInformation = DeviceInformation.builder().build();

        BlackListEntity blackListEntity = BlackListEntity.builder().build();

        Mockito.when(blackListRepository.findByRegistryDeviceIdAndDeviceHash(Mockito.any(), Mockito.any())).thenReturn(blackListEntity);
        Mockito.when(whiteListRepository.findByRegistryDeviceIdAndDeviceHash(Mockito.any(), Mockito.any())).thenReturn(null);

        Mockito.when(accountRepository.findByPhoneNumberOrderByIdAccountEntityDesc(Mockito.any())).thenReturn(null);

        DeviceEntity deviceEntity = DeviceEntity.builder()
                .isValidated(false).build();

        Mockito.when(deviceRepository.findByDeviceIdOrderByIdDeviceEntity(Mockito.any())).thenReturn(deviceEntity);

        DeviceEntity device = DeviceEntity.builder().build();
        Mockito.when(deviceRepository.findByIdDeviceEntity(Mockito.any())).thenReturn(device);

        AccountEntity account = AccountEntity.builder().build();
        Mockito.when(accountRepository.findByIdAccountEntity(Mockito.any())).thenReturn(account);

        ResponseDevice responseDeviceRegistry = ResponseDevice.builder()
                .responseCode("200")
                .build();
        Mockito.when(registryDeviceInPort.registryDevice(deviceInformation, "31234321122")).thenReturn(responseDeviceRegistry);


        SessionOTP sessionOTP = SessionOTP.builder()
                .responseCode("00")
                .responseMessage("success")
                .otp("123")
                .build();

        Mockito.when(otpInPort.generateOtp(Mockito.any())).thenReturn(sessionOTP);

        //Act
        ResponseDevice responseDevice = validateDbConnector.validateDevice(deviceInformation, "31234321122");

        //Assert
        Assertions.assertNotNull(responseDevice);
    }

    @Test
    void registryAttempTest() throws Exception {
        //Arrange

        DeviceValidationAttempEntity attempEntity = DeviceValidationAttempEntity.builder().build();

        Mockito.when(deviceValidationAttempRepository.save(Mockito.any())).thenReturn(attempEntity);
        //Act

        //Assertion
        Assertions.assertDoesNotThrow( () -> {
            validateDbConnector.registryAttemp("1234", "2321312", "123213", "ERROR");
        });

    }


    @Test
    void validateDeviceOtpBlackListTest() throws OtpException, DeviceValidatorException {
        //  Arrange

        OtpRequest request = OtpRequest.builder()
                .phoneNumber("2345656")
                .otp("23445")
                .email("")
                .build();

        SessionOTP sessionOTP = SessionOTP.builder()
                .responseCode("00")
                .responseMessage("success")
                .otp("123")
                .build();

        Mockito.when(otpInPort.validateOtp(request)).thenReturn(sessionOTP);

        DeviceInformation deviceInformation = DeviceInformation.builder().build();

        BlackListEntity blackListEntity = BlackListEntity.builder().build();
        WhiteListEntity whiteListEntity = WhiteListEntity.builder().build();

        Mockito.when(blackListRepository.findByRegistryDeviceIdAndDeviceHash(Mockito.any(), Mockito.any())).thenReturn(blackListEntity);
        Mockito.when(whiteListRepository.findByRegistryDeviceIdAndDeviceHash(Mockito.any(), Mockito.any())).thenReturn(null);

        AccountEntity accountEntity = AccountEntity.builder().build();

        Mockito.when(accountRepository.findByPhoneNumberOrderByIdAccountEntityDesc(Mockito.any())).thenReturn(accountEntity);

        DeviceEntity deviceEntity = DeviceEntity.builder().build();

        Mockito.when(deviceRepository.findByDeviceIdOrderByIdDeviceEntity(Mockito.any())).thenReturn(deviceEntity);

        //Act
        ResponseDevice responseDevice = validateDbConnector.validateDeviceOtp(deviceInformation, "31234321122", "123456");

        //Assert
        Assertions.assertNotNull(responseDevice);
    }

    @Test
    void validateDeviceOtpSuccessTest() throws OtpException, DeviceValidatorException {
        //  Arrange

        OtpRequest request = OtpRequest.builder()
                .phoneNumber("2345656")
                .otp("23445")
                .email("")
                .build();
        DeviceInformation deviceInformation = DeviceInformation.builder().build();

        Mockito.when(blackListRepository.findByRegistryDeviceIdAndDeviceHash(Mockito.any(), Mockito.any())).thenReturn(null);
        Mockito.when(whiteListRepository.findByRegistryDeviceIdAndDeviceHash(Mockito.any(), Mockito.any())).thenReturn(null);

        AccountEntity accountEntity = AccountEntity.builder()
                .isValidated(false).build();

        Mockito.when(accountRepository.findByPhoneNumberOrderByIdAccountEntityDesc(Mockito.any())).thenReturn(accountEntity);

        DeviceEntity deviceEntity = DeviceEntity.builder()
                .isValidated(false).build();

        Mockito.when(deviceRepository.findByDeviceIdOrderByIdDeviceEntity(Mockito.any())).thenReturn(deviceEntity);


        SessionOTP sessionOTP = SessionOTP.builder()
                .responseCode("00")
                .responseMessage("success")
                .otp("123")
                .build();

        Mockito.when(otpInPort.validateOtp(Mockito.any())).thenReturn(sessionOTP);

        DeviceEntity device = DeviceEntity.builder().build();
        Mockito.when(deviceRepository.findByIdDeviceEntity(Mockito.any())).thenReturn(device);

        AccountEntity account = AccountEntity.builder().build();
        Mockito.when(accountRepository.findByIdAccountEntity(Mockito.any())).thenReturn(account);

        //Act
        ResponseDevice responseDevice = validateDbConnector.validateDeviceOtp(deviceInformation, "31234321122", "123456");

        //Assert
        Assertions.assertNotNull(responseDevice);
    }

    @Test
    void validateDeviceOtpCatchTest() throws DeviceValidatorException, OtpException {
        //  Arrange

        OtpRequest request = OtpRequest.builder()
                .phoneNumber("2345656")
                .otp("23445")
                .email("")
                .build();
        DeviceInformation deviceInformation = DeviceInformation.builder().build();

        Mockito.when(blackListRepository.findByRegistryDeviceIdAndDeviceHash(Mockito.any(), Mockito.any())).thenReturn(null);
        Mockito.when(whiteListRepository.findByRegistryDeviceIdAndDeviceHash(Mockito.any(), Mockito.any())).thenReturn(null);

        AccountEntity accountEntity = AccountEntity.builder()
                .isValidated(false).build();

        Mockito.when(accountRepository.findByPhoneNumberOrderByIdAccountEntityDesc(Mockito.any())).thenReturn(accountEntity);

        DeviceEntity deviceEntity = DeviceEntity.builder()
                .isValidated(false).build();

        Mockito.when(deviceRepository.findByDeviceIdOrderByIdDeviceEntity(Mockito.any())).thenReturn(deviceEntity);


        SessionOTP sessionOTP = SessionOTP.builder()
                .responseCode("00")
                .responseMessage("success")
                .otp("123")
                .build();

        Mockito.when(otpInPort.validateOtp(Mockito.any())).thenThrow(OtpException.class);

        DeviceEntity device = DeviceEntity.builder().build();
        Mockito.when(deviceRepository.findByIdDeviceEntity(Mockito.any())).thenReturn(device);

        AccountEntity account = AccountEntity.builder().build();
        Mockito.when(accountRepository.findByIdAccountEntity(Mockito.any())).thenReturn(account);


        //Mockito.when(validateDbConnector.validateDeviceOtp(deviceInformation, "31234321122", "123456")).thenThrow(Exception.class);
        //Act Y Assert
        //Act
        ResponseDevice responseDevice = validateDbConnector.validateDeviceOtp(deviceInformation, "31234321122", "123456");

        //Assert
        Assertions.assertNotNull(responseDevice);
    }

    @Test
    void validateDeviceOtpCatch2Test() throws OtpException {
        //  Arrange

        DeviceInformation deviceInformation = DeviceInformation.builder().build();

        Mockito.when(blackListRepository.findByRegistryDeviceIdAndDeviceHash(Mockito.any(), Mockito.any())).thenReturn(null);
        Mockito.when(whiteListRepository.findByRegistryDeviceIdAndDeviceHash(Mockito.any(), Mockito.any())).thenReturn(null);

        AccountEntity accountEntity = AccountEntity.builder().build();

        Mockito.when(accountRepository.findByPhoneNumberOrderByIdAccountEntityDesc(Mockito.any())).thenReturn(accountEntity);

        DeviceEntity deviceEntity = DeviceEntity.builder().build();

        Mockito.when(deviceRepository.findByDeviceIdOrderByIdDeviceEntity(Mockito.any())).thenReturn(deviceEntity);

        Mockito.when(deviceRepository.findByIdDeviceEntity(Mockito.any())).thenReturn(null);

        Mockito.when(accountRepository.findByIdAccountEntity(Mockito.any())).thenReturn(null);

        Mockito.when(otpInPort.validateOtp(Mockito.any())).thenReturn(null);

        //Act Y Assert
        try {
            validateDbConnector.validateDeviceOtp(deviceInformation, "31234321122", "123456");
        } catch (DeviceValidatorException e) {
            Assertions.assertTrue(e instanceof  DeviceValidatorException);
        }



    }



}
