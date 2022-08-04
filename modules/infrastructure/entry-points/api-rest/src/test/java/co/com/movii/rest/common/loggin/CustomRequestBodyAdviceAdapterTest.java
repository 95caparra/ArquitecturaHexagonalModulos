package co.com.movii.rest.common.loggin;

import co.com.movii.rest.common.logging.CustomRequestBodyAdviceAdapter;
import co.com.movii.rest.common.logging.LoggingService;
import co.com.movii.rest.device.DeviceValidatorRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.http.MockHttpInputMessage;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;

class CustomRequestBodyAdviceAdapterTest {

    @InjectMocks
    CustomRequestBodyAdviceAdapter customRequestBodyAdviceAdapter;

    @Mock
    LoggingService loggingService;

    @Mock
    HttpServletRequest httpServletRequest;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void afterBodyReadTest(){
        //Arrange
        DeviceValidatorRequestDTO deviceValidatorRequestDTO = DeviceValidatorRequestDTO.builder().build();

        ByteArrayInputStream inputStream = new ByteArrayInputStream("3\n4\n".getBytes());
        MockHttpInputMessage inputMessage = new MockHttpInputMessage(inputStream);

        //Act y Assert
        Assertions.assertDoesNotThrow( () -> {
            customRequestBodyAdviceAdapter.afterBodyRead(deviceValidatorRequestDTO, inputMessage,
                    null, null, null);
        });
    }

}
