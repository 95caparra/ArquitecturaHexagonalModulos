package co.com.movii.device;

import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;
import co.com.movii.ports.out.ValidateDeviceOutPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ValidateDeviceServiceTest {

    @InjectMocks
    ValidateDeviceService validateDeviceService;

    @Mock
    ValidateDeviceOutPort validateDeviceOutPort;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateDeviceTest() throws DeviceValidatorException {
        //Arrange
        DeviceInformation deviceInformation = DeviceInformation.builder()
                .deviceId("deviceId")
                .deviceName("deviceName")
                .deviceResolution("deviceResolution")
                .deviceManufacturer("deviceManufacturer")
                .apiLevel("apiLevel")
                .isFingerCompatible(false)
                .build();

        ResponseDevice responseDevice = ResponseDevice.builder().build();

        Mockito.when(validateDeviceOutPort.validateDevice(Mockito.any(), Mockito.any())).thenReturn(responseDevice);

        //Act
        ResponseDevice response = validateDeviceService.validateDevice(deviceInformation, "123412344");

        //Asserts
        Assertions.assertNotNull(response);

    }
}
