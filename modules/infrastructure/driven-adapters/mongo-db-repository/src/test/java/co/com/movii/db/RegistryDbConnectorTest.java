package co.com.movii.db;

import co.com.movii.entities.DeviceInformationEntity;
import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.ports.in.RegistryDeviceInPort;
import co.com.movii.repository.AccountRepository;
import co.com.movii.repository.DeviceInformationRepository;
import co.com.movii.repository.DeviceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class RegistryDbConnectorTest {

    @InjectMocks
    RegistryDbConnector registryDbConnector;

    @Mock
    DeviceInformationRepository deviceInformationRepository;

    @Mock
    DeviceRepository deviceRepository;

    @Mock
    AccountRepository accountRepository;


    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registryDeviceTest() throws Exception {
        //  Arrange
        DeviceInformation deviceInformation = DeviceInformation.builder().build();
        //Act
        ResponseDevice responseDevice = registryDbConnector.registryDevice(deviceInformation, "31234321122");

        //Assert
        Assertions.assertNotNull(responseDevice);


    }
}
