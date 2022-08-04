package co.com.movii.rest.common;

import co.com.movii.model.exceptions.ModelException;
import co.com.movii.rest.common.statuscode.StatusCode;
import co.com.movii.rest.common.statuscode.StatusCodeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public class HandlerControllerExceptions {

    @Autowired
    private StatusCodeConfig statusCodeConfig;


    /**
     * Handle error
     *
     * @param e exception
     * @return response
     */
    @ExceptionHandler({Throwable.class})
    public ResponseEntity<ResponseCommand> exceptionHandler(Throwable e) {
        return handlerError(e, null, null, null);
    }

    /**
     * Handler error
     *
     * @param e exception
     * @return response
     */
    @ExceptionHandler({ModelException.class})
    public ResponseEntity<ResponseCommand> modelExceptionHandler(ModelException e) {
        if (e.getError() == null) {
            return exceptionHandler(e);
        }
        var statusCode = statusCodeConfig.of(e.getError());
        return handlerError(e, null, statusCode, e.getDefaultMessage());
    }

    private ResponseEntity<ResponseCommand> handlerError(Throwable e,
                                                         String subMessage,
                                                         StatusCode statusCode,
                                                         String defaultMessage) {
        printMessage(e);
        var finalStatusCode = statusCode;
        if (finalStatusCode == null) {
            var statusCodes = StatusCodes.getByClass(e.getClass());
            finalStatusCode = statusCodeConfig.of(statusCodes.name());
        }
        var httpStatus = getHttpStatus(finalStatusCode);
        var message = getMessage(finalStatusCode, defaultMessage, subMessage);
        return ResponseEntity.ok(ResponseCommand.builder()
                .code(String.valueOf(httpStatus.value()))
                .message(message)
                .build());
    }

    public String getMessage(StatusCode statusCode, String defaultMessage, String subMessage) {
        var message = statusCode.getMessage();
        if (defaultMessage != null && statusCode.getCode().equalsIgnoreCase("99")) {
            message = defaultMessage;
        }

        if (subMessage != null) {
            message = message.concat(": ").concat(subMessage);
        }

        return message;
    }

    public void printMessage(Throwable e) {
        if (e.getMessage() != null) {
            log.info("{}: {}", e.getClass().getSimpleName(), e.getMessage());
        } else {
            log.info("{}", e.getClass().getSimpleName());
        }
    }

    /**
     * Get HttpStatus by statusCode
     *
     * @param statusCode status code
     * @return httpStatus
     */
    public HttpStatus getHttpStatus(StatusCode statusCode) {
        try {
            var httpStatus = HttpStatus.resolve(Integer.parseInt(statusCode.getCode()));
            if (httpStatus == null) {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return httpStatus;
        } catch (NumberFormatException e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
