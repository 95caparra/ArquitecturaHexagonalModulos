package co.com.movii.rest.device.validate;

import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;
import co.com.movii.ports.in.ValidateDeviceInPort;
import co.com.movii.rest.device.DeviceValidatorRequestDTO;
import co.com.movii.rest.device.DeviceValidatorResponseDTO;
import co.com.movii.rest.device.validateotp.DeviceValidatorOtpController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class DeviceValidatorControllerTest {

    @InjectMocks
    DeviceValidatorController deviceValidatorController;

    @Mock
    ValidateDeviceInPort validateDeviceInPort;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validationDeviceTest() throws DeviceValidatorException {
        //Arrange
        DeviceValidatorRequestDTO deviceInformationDTO = DeviceValidatorRequestDTO.builder().build();

        DeviceInformation deviceInformation = DeviceInformation.builder().build();
        ResponseDevice responseDevice = ResponseDevice.builder().build();

        Mockito.when(validateDeviceInPort.validateDevice(deviceInformation, "3212123321")).thenReturn(responseDevice);
        //Act
        DeviceValidatorResponseDTO responseDTO = deviceValidatorController.validationDevice("3212123321",  deviceInformationDTO);

        //Assert
        Assertions.assertNotNull(responseDTO);
    }
}
