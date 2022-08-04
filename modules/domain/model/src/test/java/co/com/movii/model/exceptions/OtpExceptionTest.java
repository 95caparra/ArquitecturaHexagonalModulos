package co.com.movii.model.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class OtpExceptionTest {

    @Test
    void constructorWithErrors(){
        //Act
        OtpException exception = new OtpException(Mockito.any(), Mockito.any());
        //Assert
        Assertions.assertNotNull(exception);
    }

    @Test
    void constructorThrow(){
        //Arrange
        Throwable t = new Exception();
        //Act
        OtpException exception = new OtpException(t);
        //Assert
        Assertions.assertNotNull(exception);
    }
}
