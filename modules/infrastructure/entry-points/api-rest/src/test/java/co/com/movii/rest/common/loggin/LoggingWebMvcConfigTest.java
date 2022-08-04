package co.com.movii.rest.common.loggin;

import co.com.movii.rest.common.logging.LoggingInterceptor;
import co.com.movii.rest.common.logging.LoggingWebMvcConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.webmvc.ui.SwaggerIndexTransformer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

class LoggingWebMvcConfigTest {


    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void constructor(){
        //Act
        SwaggerUiConfigParameters swaggerUiConfigParameters = Mockito.mock(SwaggerUiConfigParameters.class);
        SwaggerIndexTransformer swaggerIndexTransformer = Mockito.mock(SwaggerIndexTransformer.class);

        LoggingInterceptor loggingInterceptor1  = Mockito.mock(LoggingInterceptor.class);

        LoggingWebMvcConfig loggingWebMvcConfig = new LoggingWebMvcConfig(swaggerUiConfigParameters,
                swaggerIndexTransformer,
                null,
                loggingInterceptor1);
        //Assert
        Assertions.assertNotNull(loggingWebMvcConfig);
    }

    @Test
    void LoggingWebMvcConfigTest(){
        //Arrange
        InterceptorRegistry registry = new InterceptorRegistry();

        LoggingWebMvcConfig loggingWebMvcConfig = Mockito.mock(LoggingWebMvcConfig.class);

        //Act y Asserts
        Assertions.assertDoesNotThrow( () -> {
            loggingWebMvcConfig.addInterceptors(registry);
        });
    }
}
