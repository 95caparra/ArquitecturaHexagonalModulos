package co.com.movii.rest.device.validateotp;

import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;
import co.com.movii.ports.in.ValidateDeviceOtpInPort;
import co.com.movii.rest.device.DeviceValidatorRequestDTO;
import co.com.movii.rest.device.DeviceValidatorResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class DeviceValidatorOtpControllerTest {

    @InjectMocks
    DeviceValidatorOtpController deviceValidatorOtpController;

    @Mock
    ValidateDeviceOtpInPort validateDeviceOtpInPort;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validationOtpDeviceTest() throws DeviceValidatorException {
        //Arrange
        DeviceValidatorRequestDTO deviceInformationDTO = DeviceValidatorRequestDTO.builder().build();

        DeviceInformation deviceInformation = DeviceInformation.builder().build();
        ResponseDevice responseDevice = ResponseDevice.builder().build();

        Mockito.when(validateDeviceOtpInPort.validateDeviceOtp(deviceInformation, "3212123321", "1232")).thenReturn(responseDevice);
        //Act
        DeviceValidatorResponseDTO responseDTO = deviceValidatorOtpController.validationDeviceOtp("3212123321", "1232", deviceInformationDTO);

        //Assert
        Assertions.assertNotNull(responseDTO);
    }
}
