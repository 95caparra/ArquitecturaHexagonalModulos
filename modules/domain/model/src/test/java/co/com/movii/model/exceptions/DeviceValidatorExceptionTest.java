package co.com.movii.model.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DeviceValidatorExceptionTest {

    @Test
    void constructorWithErrors(){
        //Act
        DeviceValidatorException exception = new DeviceValidatorException(Mockito.any(), Mockito.any());
        //Assert
        Assertions.assertNotNull(exception);
    }

    @Test
    void constructorThrow(){
        //Arrange
        Throwable t = new Exception();
        //Act
        DeviceValidatorException exception = new DeviceValidatorException(t);
        //Assert
        Assertions.assertNotNull(exception);
    }
}
