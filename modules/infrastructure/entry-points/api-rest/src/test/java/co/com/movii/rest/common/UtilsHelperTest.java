package co.com.movii.rest.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class UtilsHelperTest {

    @InjectMocks
    UtilsHelper helper;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void protectFieldsTest(){
        //Arrange
        //Mockito.when(helper.protectFields("valor", "valor")).thenCallRealMethod();
        //Act
        String result = helper.protectFields("valor", "valor");
        //Asserts
        Assertions.assertNotNull(result);
    }

    @Test
    void protectFieldsJsonTest(){
        //Arrange
        //Mockito.when(helper.protectFields("valor", "valor")).thenCallRealMethod();
        //Act
        String result = helper.protectFields("valor", ".*&quot;m&quot;:\\[&quot;value&quot;,569,&quot;time&quot;,\\{&quot;time&quot;:&quot;\\+00000001952-03-11.*");
        //Asserts
        Assertions.assertNotNull(result);
    }

    @Test
    void printIgnoreTest(){
        //Arrange
        //Mockito.when(helper.printIgnore("valor", "valor")).thenCallRealMethod();
        //Act
        String result = helper.printIgnore("valor", "valor");
        //Asserts
        Assertions.assertNotNull(result);
    }

    @Test
    void assignCorrelativeTest(){
        //Arrange
        //Act y Asserts
        Assertions.assertDoesNotThrow( () -> {
            helper.assignCorrelative("valor", "valor");
        });
    }

    @Test
    void assignCorrelativeNullTest(){
        //Arrange
        //Mockito.when(helper.printIgnore("valor", "valor")).thenCallRealMethod();
        //Act y Asserts
        Assertions.assertDoesNotThrow( () -> {
            helper.assignCorrelative("valor", null);
        });


    }

    @Test
    void getCorrelativeTest(){
        //Arrange
        //Mockito.when(helper.printIgnore("valor", "valor")).thenCallRealMethod();
        //Act
        String result = helper.getCorrelative();
        //Asserts
        Assertions.assertNotNull(result);
    }
}
