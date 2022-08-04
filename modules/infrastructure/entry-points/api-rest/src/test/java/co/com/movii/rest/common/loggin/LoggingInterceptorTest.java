package co.com.movii.rest.common.loggin;

import co.com.movii.rest.common.logging.LoggingInterceptor;
import co.com.movii.rest.common.logging.LoggingService;
import co.com.movii.rest.device.DeviceValidatorRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.DispatcherType;

class LoggingInterceptorTest {

    @InjectMocks
    LoggingInterceptor loggingInterceptor;

    @Mock
    LoggingService loggingService;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void preHandleTest(){

        //Arrange
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();

        request.setDispatcherType(DispatcherType.valueOf("REQUEST"));
        request.setMethod(HttpMethod.GET.name());
        DeviceValidatorRequestDTO deviceValidatorRequestDTO = DeviceValidatorRequestDTO.builder().build();

        //Act
        Boolean bandera = loggingInterceptor.preHandle(request, response, deviceValidatorRequestDTO);

        //Assert
        Assertions.assertNotNull(bandera);

    }
}
