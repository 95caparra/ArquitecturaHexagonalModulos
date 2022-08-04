package co.com.movii.rest.common.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingInterceptor implements AsyncHandlerInterceptor {

    private final LoggingService loggingService;

    /**
     * Handle Get requests
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return process
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {

        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                && request.getMethod().equals(HttpMethod.GET.name())) {
            try {
                loggingService.logRequest(request, "{}");
            } catch (JsonProcessingException e) {
                log.error("Error logging request: {}", e.getMessage());
            }
        }

        return true;
    }
}