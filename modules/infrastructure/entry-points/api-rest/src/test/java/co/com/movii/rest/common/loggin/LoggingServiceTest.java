package co.com.movii.rest.common.loggin;

import co.com.movii.rest.common.logging.LoggingService;
import co.com.movii.rest.device.DeviceValidatorRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


class LoggingServiceTest {

    @InjectMocks
    LoggingService loggingService;

    @Mock
    Environment environment;

    @Mock
    ObjectMapper mapper;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void logRequestTest() throws JsonProcessingException {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        DeviceValidatorRequestDTO deviceValidatorRequestDTO = DeviceValidatorRequestDTO.builder().build();

        Mockito.when(environment.getProperty("component.name")).thenReturn("test-component");

        //Act y Assert
        Assertions.assertDoesNotThrow( () -> {
            loggingService.logRequest(request, deviceValidatorRequestDTO);
        });
    }

    @Test
    void logResponseTest() throws JsonProcessingException {

        //Arrange
        MockHttpServletResponse response = new MockHttpServletResponse();
        DeviceValidatorRequestDTO deviceValidatorRequestDTO = DeviceValidatorRequestDTO.builder().build();

        Mockito.when(environment.getProperty("component.name")).thenReturn("test-component");

        //Act y Assert
        Assertions.assertDoesNotThrow( () -> {
            loggingService.logResponse(response, deviceValidatorRequestDTO);
        });
    }
}
