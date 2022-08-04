package client;

import co.com.movii.client.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ServiceExceptionTest {



    @Test
    void constructor(){
        //Arrange
        Throwable t = new Exception();
        //Act
        ServiceException mahindraConnector = new ServiceException (t);
        //Assert
        Assertions.assertNotNull(mahindraConnector);
    }
}
