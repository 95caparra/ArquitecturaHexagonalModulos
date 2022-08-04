package co.com.movii.rest.common.logging;

import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.providers.ActuatorProvider;
import org.springdoc.webmvc.ui.SwaggerIndexTransformer;
import org.springdoc.webmvc.ui.SwaggerWebMvcConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.Optional;

@Configuration
public class LoggingWebMvcConfig extends SwaggerWebMvcConfigurer {

    private final LoggingInterceptor loggingInterceptor;

    public LoggingWebMvcConfig(SwaggerUiConfigParameters swaggerUiConfigParameters,
                               SwaggerIndexTransformer swaggerIndexTransformer,
                               Optional<ActuatorProvider> actuatorProvider,
                               LoggingInterceptor loggingInterceptorI) {
        super(swaggerUiConfigParameters, swaggerIndexTransformer, actuatorProvider);
        this.loggingInterceptor = loggingInterceptorI;
    }

    /**
     * Add interceptors
     *
     * @param registry interceptors registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }
}