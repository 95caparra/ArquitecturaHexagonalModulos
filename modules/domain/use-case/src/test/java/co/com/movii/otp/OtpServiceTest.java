package co.com.movii.otp;

import co.com.movii.model.OtpRequest;
import co.com.movii.model.exceptions.OtpException;
import co.com.movii.model.session.SessionOTP;
import co.com.movii.ports.out.OtpOutPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class OtpServiceTest {

    @InjectMocks
    OtpService otpService;

    @Mock
    OtpOutPort otpOutPort;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateOtpTest() throws OtpException {
        // Arrange
        OtpRequest otpRequest = OtpRequest.builder()
                .otp("otp")
                .phoneNumber("phoneNumber")
                .email("email")
                .build();

        SessionOTP sessionOTP = SessionOTP.builder().build();

        Mockito.when(otpOutPort.generateOtp(Mockito.any())).thenReturn(sessionOTP);

        //Act
        SessionOTP response = otpService.generateOtp(otpRequest);

        //Asserts
        Assertions.assertNotNull(response);
    }

    @Test
    void validateOtpTest() throws OtpException {
        // Arrange
        OtpRequest otpRequest = OtpRequest.builder()
                .otp("otp")
                .phoneNumber("phoneNumber")
                .email("email")
                .build();

        SessionOTP sessionOTP = SessionOTP.builder().build();

        Mockito.when(otpOutPort.validateOtp(Mockito.any())).thenReturn(sessionOTP);

        //Act
        SessionOTP response = otpService.validateOtp(otpRequest);

        //Asserts
        Assertions.assertNotNull(response);
    }

}
