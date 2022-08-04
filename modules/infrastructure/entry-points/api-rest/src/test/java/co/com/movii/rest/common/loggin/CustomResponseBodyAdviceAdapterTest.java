package co.com.movii.rest.common.loggin;

import co.com.movii.rest.common.logging.CustomResponseBodyAdviceAdapter;
import co.com.movii.rest.common.logging.LoggingService;
import co.com.movii.rest.device.DeviceValidatorRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.ByteArrayInputStream;

class CustomResponseBodyAdviceAdapterTest {

    @InjectMocks
    CustomResponseBodyAdviceAdapter customRequestBodyAdviceAdapter;

    @Mock
    LoggingService loggingService;


    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void beforeBodyWriteTest() {
        //Arrange
        DeviceValidatorRequestDTO deviceValidatorRequestDTO = DeviceValidatorRequestDTO.builder().build();

        ByteArrayInputStream inputStream = new ByteArrayInputStream("3\n4\n".getBytes());
        MockHttpInputMessage inputMessage = new MockHttpInputMessage(inputStream);
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        //Act
        Object object = customRequestBodyAdviceAdapter.beforeBodyWrite(deviceValidatorRequestDTO, null,
                null, null, null, null);

        //Assert
        Assertions.assertNotNull(object);
    }

}
