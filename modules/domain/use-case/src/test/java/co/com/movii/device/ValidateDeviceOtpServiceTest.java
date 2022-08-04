package co.com.movii.device;

import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;
import co.com.movii.ports.out.ValidateDeviceOtpOutPort;
import co.com.movii.ports.out.ValidateDeviceOutPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ValidateDeviceOtpServiceTest {

    @InjectMocks
    ValidateDeviceOtpService validateDeviceOtpService;

    @Mock
    ValidateDeviceOtpOutPort validateDeviceOtpOutPort;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateDeviceOtpTest() throws DeviceValidatorException {
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

        Mockito.when(validateDeviceOtpOutPort.validateDeviceOtp(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(responseDevice);

        //Act
        ResponseDevice response = validateDeviceOtpService.validateDeviceOtp(deviceInformation, "123412344", "1234" );

        //Asserts
        Assertions.assertNotNull(response);

    }
}
