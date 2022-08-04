package co.com.movii.device;

import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.ports.out.RegistryDeviceOutPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class RegistryDeviceServiceTest {

    @InjectMocks
    RegistryDeviceService registryDeviceService;

    @Mock
    RegistryDeviceOutPort registryDeviceOutPort;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registryDevice() throws Exception {
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

        Mockito.when(registryDeviceOutPort.registryDevice(Mockito.any(), Mockito.any())).thenReturn(responseDevice);

        //Act
        ResponseDevice response = registryDeviceService.registryDevice(deviceInformation, "123412344");

        //Asserts
        Assertions.assertNotNull(response);

    }
}
