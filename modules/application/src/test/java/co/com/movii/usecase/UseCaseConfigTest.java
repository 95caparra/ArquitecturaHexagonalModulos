package co.com.movii.usecase;

import co.com.movii.ports.in.OtpInPort;
import co.com.movii.ports.in.RegistryDeviceInPort;
import co.com.movii.ports.in.ValidateDeviceInPort;
import co.com.movii.ports.in.ValidateDeviceOtpInPort;
import co.com.movii.ports.out.OtpOutPort;
import co.com.movii.ports.out.RegistryDeviceOutPort;
import co.com.movii.ports.out.ValidateDeviceOtpOutPort;
import co.com.movii.ports.out.ValidateDeviceOutPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UseCaseConfigTest {

    @InjectMocks
    UseCaseConfig config;

    @Mock
    RegistryDeviceOutPort  registryDeviceOutPort;

    @Mock
    RegistryDeviceInPort registryDeviceInPort;

    @Mock
    ValidateDeviceOutPort validateDeviceOutPort;

    @Mock
    ValidateDeviceInPort validateDeviceInPort;

    @Mock
    ValidateDeviceOtpOutPort validateDeviceOtpOutPort;

    @Mock
    ValidateDeviceOtpInPort validateDeviceOtpInPort;

    @Mock
    OtpOutPort otpOutPort;

    @Mock
    OtpInPort otpInPort;


    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registryDeviceTest()  {
        //Arrange

        //Act
        registryDeviceInPort = config.registryDeviceUseCase(registryDeviceOutPort);
        //Asserts
        Assertions.assertNotNull(registryDeviceInPort);

    }

    @Test
    void validateDeviceTest(){
        //Arrange

        //Act
        validateDeviceInPort = config.validateDeviceUseCase(validateDeviceOutPort);
        //Asserts
        Assertions.assertNotNull(validateDeviceInPort);
    }

    @Test
    void validateDeviceOtpTest(){
        //Arrange

        //Act
        validateDeviceOtpInPort = config.validateDeviceOtpUseCase(validateDeviceOtpOutPort);
        //Asserts
        Assertions.assertNotNull(validateDeviceOtpInPort);
    }

    @Test
    void otpTest(){
        //Arrange

        //Act
        otpInPort = config.otpUseCase(otpOutPort);
        //Asserts
        Assertions.assertNotNull(otpInPort);
    }


}
