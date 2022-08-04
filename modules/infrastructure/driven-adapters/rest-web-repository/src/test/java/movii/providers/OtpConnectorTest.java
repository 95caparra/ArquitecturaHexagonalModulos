package movii.providers;

import co.com.movii.client.ServiceException;
import co.com.movii.model.OtpRequest;
import co.com.movii.model.exceptions.OtpException;
import co.com.movii.model.session.SessionOTP;
import co.com.movii.providers.otp.OtpConnector;
import co.com.movii.providers.otp.OtpDTO;
import co.com.movii.providers.otp.OtpFactory;
import co.com.movii.providers.otp.OtpProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

class OtpConnectorTest {

    @Mock
    OtpConnector otpConnector;
    @Mock
    OtpProperties properties;
    @Mock
    OtpFactory factory;
    @Mock
    Environment environment;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void constructor(){
        //Act
        OtpConnector otpConnector1 = new OtpConnector(properties, factory, environment);
        //Assert
        Assertions.assertNotNull(otpConnector1);
    }

    @Test
    void generateSuccessTest() throws OtpException, ServiceException {
        //Arrange
        Mockito.when(otpConnector.getProviderFactory()).thenReturn(factory);
        Mockito.when(otpConnector.getProviderProperties()).thenReturn(properties);
        Mockito.when(otpConnector.getEnvironment()).thenReturn(environment);
        Mockito.when(otpConnector.generateOtp(Mockito.any())).thenCallRealMethod();
        OtpRequest request = OtpRequest.builder()
                .phoneNumber("2345656")
                .build();

        OtpDTO otpResponseDTO = OtpDTO.builder()
                .url("")
                .email("email")
                .otp("otp")
                .responseCode("00")
                .build();

        OtpDTO otpRequestDTO = OtpDTO.builder().build();
        Mockito.when(factory.getOtp(Mockito.any())).thenReturn(otpRequestDTO);

        Mockito.when(otpConnector.invoke(Mockito.any(), Mockito.any(),
                Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(otpResponseDTO);

        Mockito.when(factory.getProviderProperties()).thenReturn(properties);

        Mockito.when(properties.getUrl()).thenReturn("https://otp");
        Mockito.when(factory.getGenerateOtp()).thenReturn("https://otp");
        //Act
        SessionOTP response = otpConnector.generateOtp(request);
        //Asserts
        Assertions.assertNotNull(response);
    }

    @Test
    void generateIfTest() throws OtpException, ServiceException {
        //Arrange
        Mockito.when(otpConnector.getProviderFactory()).thenReturn(factory);
        Mockito.when(otpConnector.getProviderProperties()).thenReturn(properties);
        Mockito.when(otpConnector.getEnvironment()).thenReturn(environment);
        Mockito.when(otpConnector.generateOtp(Mockito.any())).thenCallRealMethod();
        OtpRequest request = OtpRequest.builder()
                .phoneNumber("2345656")
                .build();

        OtpDTO otpResponseDTO = OtpDTO.builder()
                .url("")
                .email("email")
                .otp("otp")
                .responseCode("99")
                .build();

        OtpDTO otpRequestDTO = OtpDTO.builder().build();
        Mockito.when(factory.getOtp(Mockito.any())).thenReturn(otpRequestDTO);

        Mockito.when(otpConnector.invoke(Mockito.any(), Mockito.any(),
                Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(otpResponseDTO);

        Mockito.when(factory.getProviderProperties()).thenReturn(properties);
        Mockito.when(properties.getUrl()).thenReturn("https://otp");
        Mockito.when(factory.getGenerateOtp()).thenReturn("https://otp");
        //Act
        try {
            otpConnector.generateOtp(request);
        } catch (OtpException e){
            //Asserts
            Assertions.assertTrue(e instanceof  OtpException);
        }
    }

    @Test
    void generateCatchTest() throws OtpException, ServiceException {
        //Arrange
        Mockito.when(otpConnector.getProviderFactory()).thenReturn(factory);
        Mockito.when(otpConnector.getProviderProperties()).thenReturn(properties);
        Mockito.when(otpConnector.getEnvironment()).thenReturn(environment);
        Mockito.when(otpConnector.generateOtp(Mockito.any())).thenCallRealMethod();
        OtpRequest request = OtpRequest.builder()
                .phoneNumber("2345656")
                .build();

        OtpDTO otpRequestDTO = OtpDTO.builder().build();
        Mockito.when(factory.getOtp(Mockito.any())).thenReturn(otpRequestDTO);

        Mockito.when(factory.getProviderProperties()).thenReturn(properties);

        Mockito.when(otpConnector.invoke(Mockito.any(), Mockito.any(),
                Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(ServiceException.class);

        Mockito.when(properties.getUrl()).thenReturn("https://otp");
        Mockito.when(factory.getGenerateOtp()).thenReturn("https://otp");
        //Act y Asserts
        OtpException exption = Assertions
                .assertThrows(OtpException.class, () -> {
                    otpConnector.generateOtp(request);
                }, "OtpException error was expected");

        Assertions.assertTrue(exption instanceof  OtpException);
    }


    @Test
    void validateSuccessTest() throws OtpException, ServiceException {
        //Arrange
        Mockito.when(otpConnector.getProviderFactory()).thenReturn(factory);
        Mockito.when(otpConnector.getProviderProperties()).thenReturn(properties);
        Mockito.when(otpConnector.getEnvironment()).thenReturn(environment);
        Mockito.when(otpConnector.validateOtp(Mockito.any())).thenCallRealMethod();
        OtpRequest request = OtpRequest.builder()
                .phoneNumber("2345656")
                .otp("23445")
                .build();

        OtpDTO otpResponseDTO = OtpDTO.builder()
                .url("")
                .email("email")
                .otp("otp")
                .responseCode("00")
                .build();

        Mockito.when(factory.getValidateOtp()).thenReturn("otpRequestDTO");

        Mockito.when(otpConnector.invoke(Mockito.any(), Mockito.any(),
                Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(otpResponseDTO);

        Mockito.when(factory.getProviderProperties()).thenReturn(properties);
        Mockito.when(properties.getUrl()).thenReturn("https://otp");
        Mockito.when(factory.getGenerateOtp()).thenReturn("https://otp");
        //Act
        SessionOTP response = otpConnector.validateOtp(request);
        //Asserts
        Assertions.assertNotNull(response);
    }

    @Test
    void validateIfTest() throws OtpException, ServiceException {
        //Arrange
        Mockito.when(otpConnector.getProviderFactory()).thenReturn(factory);
        Mockito.when(otpConnector.getProviderProperties()).thenReturn(properties);
        Mockito.when(otpConnector.getEnvironment()).thenReturn(environment);
        Mockito.when(otpConnector.validateOtp(Mockito.any())).thenCallRealMethod();
        OtpRequest request = OtpRequest.builder()
                .phoneNumber("2345656")
                .otp("23445")
                .build();

        OtpDTO otpResponseDTO = OtpDTO.builder()
                .url("")
                .email("email")
                .otp("otp")
                .responseCode("99")
                .build();

        OtpDTO otpRequestDTO = OtpDTO.builder().build();
        Mockito.when(factory.getValidateOtp()).thenReturn("otpRequestDTO");

        Mockito.when(otpConnector.invoke(Mockito.any(), Mockito.any(),
                Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(otpResponseDTO);

        Mockito.when(factory.getProviderProperties()).thenReturn(properties);
        Mockito.when(properties.getUrl()).thenReturn("https://otp");
        Mockito.when(factory.getGenerateOtp()).thenReturn("https://otp");
        //Act
        try {
            otpConnector.validateOtp(request);
        } catch (OtpException e){
            //Asserts
            Assertions.assertTrue(e instanceof  OtpException);
        }
    }

    @Test
    void validateCatchTest() throws OtpException, ServiceException {
        //Arrange
        Mockito.when(otpConnector.getProviderFactory()).thenReturn(factory);
        Mockito.when(otpConnector.getProviderProperties()).thenReturn(properties);
        Mockito.when(otpConnector.getEnvironment()).thenReturn(environment);
        Mockito.when(otpConnector.validateOtp(Mockito.any())).thenCallRealMethod();
        OtpRequest request = OtpRequest.builder()
                .phoneNumber("2345656")
                .otp("1233")
                .build();

        Mockito.when(factory.getValidateOtp()).thenReturn("otpRequestDTO");

        Mockito.when(factory.getProviderProperties()).thenReturn(properties);

        Mockito.when(otpConnector.invoke(Mockito.any(), Mockito.any(),
                Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(ServiceException.class);

        Mockito.when(properties.getUrl()).thenReturn("https://otp");
        Mockito.when(factory.getGenerateOtp()).thenReturn("https://otp");
        //Act y Asserts
        OtpException exption = Assertions
                .assertThrows(OtpException.class, () -> {
                    otpConnector.validateOtp(request);
                }, "OtpException error was expected");

        Assertions.assertTrue(exption instanceof  OtpException);
    }

}
