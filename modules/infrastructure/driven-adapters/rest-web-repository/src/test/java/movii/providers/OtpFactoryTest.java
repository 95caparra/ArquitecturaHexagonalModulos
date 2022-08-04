package movii.providers;

import co.com.movii.model.OtpRequest;
import co.com.movii.providers.otp.OtpDTO;
import co.com.movii.providers.otp.OtpFactory;
import co.com.movii.providers.otp.OtpProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class OtpFactoryTest {

    @Mock
    OtpFactory otpFactory;

    @Mock
    OtpProperties properties;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);

    }
    @Test
    void constructor(){
        //Act
        OtpFactory factory = new OtpFactory(properties);
        //Assert
        Assertions.assertNotNull(factory);
    }


    @Test
    void generateOtpTest(){
        //Arrange
        Mockito.when(otpFactory.getProviderProperties()).thenReturn(properties);

        OtpDTO otpDTO = OtpDTO.builder().build();

        Mockito.when(otpFactory.getProviderProperties().getOtp()).thenReturn(otpDTO);

        OtpRequest request = OtpRequest.builder()
                .phoneNumber("432323432")
                .otp("1234")
                .build();

        Mockito.when(otpFactory.getOtp(Mockito.any())).thenCallRealMethod();
        //Act
        OtpDTO result = otpFactory.getOtp(request);
        //Asserts
        Assertions.assertNotNull(result);
    }


    @Test
    void validateOtpTest(){
        //Arrange
        Mockito.when(otpFactory.getProviderProperties()).thenReturn(properties);


        Mockito.when(otpFactory.getProviderProperties().getPathValidate()).thenReturn("otpDTO");

        OtpRequest request = OtpRequest.builder()
                .phoneNumber("432323432")
                .otp("1234")
                .build();

        Mockito.when(otpFactory.getValidateOtp()).thenCallRealMethod();
        //Act
        String result = otpFactory.getValidateOtp();
        //Asserts
        Assertions.assertNotNull(result);
    }
}
