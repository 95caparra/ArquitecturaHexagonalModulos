package client;

import co.com.movii.client.IModel;
import co.com.movii.providers.otp.OtpDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class IModelTest {


    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void protectedToStringTest(){
        //Arrange

        OtpDTO otpDTO = OtpDTO.builder().build();
        //Act
        String result = otpDTO.protectedToString();
        //Assert
        Assertions.assertNotNull(result);
    }

    @Test
    void protectedToStringTest2(){
        //Arrange

        OtpDTO otpDTO = OtpDTO.builder().build();
        //Act
        String result = otpDTO.protectedToJsonString();
        //Assert
        Assertions.assertNotNull(result);
    }

    @Test
    void toJsonStringTest() throws JsonProcessingException {
        //Arrange

        OtpDTO otpDTO = OtpDTO.builder().build();
        //Act
        String result = otpDTO.toJsonString();
        //Assert
        Assertions.assertNotNull(result);
    }

    @Test
    void toJsonTest() {
        //Arrange

        OtpDTO otpDTO = OtpDTO.builder().build();
        //Act
        String result = otpDTO.toJson();
        //Assert
        Assertions.assertNotNull(result);
    }

    @Test
    void toStringTest()  {
        //Arrange

        IModel iModel = Mockito.mock(IModel.class);
        Mockito.when(iModel.toString()).thenCallRealMethod();
        //Act
        String result = iModel.toString();
        //Assert
        Assertions.assertNull(result);
    }





}
