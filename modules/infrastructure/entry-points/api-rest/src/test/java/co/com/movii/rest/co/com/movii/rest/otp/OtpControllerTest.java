package co.com.movii.rest.co.com.movii.rest.otp;

import co.com.movii.model.OtpRequest;
import co.com.movii.model.exceptions.OtpException;
import co.com.movii.model.session.SessionOTP;
import co.com.movii.ports.in.OtpInPort;
import co.com.movii.rest.otp.OtpController;
import co.com.movii.rest.otp.OtpRequestDTO;
import co.com.movii.rest.otp.OtpResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class OtpControllerTest {

    @InjectMocks
    OtpController otpController;

    @Mock
    OtpInPort otpInPort;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void otpTest() throws OtpException {
        //Arrange
        SessionOTP sessionOTP = SessionOTP.builder().build();
        OtpRequest request = OtpRequest.builder().build();
        OtpRequestDTO requestDTO = OtpRequestDTO.builder().build();

        Mockito.when(otpInPort.generateOtp(request)).thenReturn(sessionOTP);
        //Act
        OtpResponseDTO otpResponseDTO = otpController.otp(requestDTO);

        //Assert
        Assertions.assertNotNull(otpResponseDTO);
    }
}
