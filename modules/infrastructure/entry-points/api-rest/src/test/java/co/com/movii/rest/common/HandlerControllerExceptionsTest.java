package co.com.movii.rest.common;

import co.com.movii.model.exceptions.ModelException;
import co.com.movii.rest.common.statuscode.StatusCode;
import co.com.movii.rest.common.statuscode.StatusCodeConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class HandlerControllerExceptionsTest {

    @InjectMocks
    HandlerControllerExceptions handlerControllerExceptions;

    @Mock
    StatusCodeConfig statusCodeConfig;

    @Mock
    StatusCode statusCode;



    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void modelExceptionHandlerTest(){

        ModelException modelException = new ModelException("error", "error");

        Mockito.when(statusCodeConfig.of(modelException.getError()))
                .thenReturn(new StatusCode(StatusCode.Level.FAIL, StatusCode.Level.FAIL.value(), modelException.getMessage(), "code"));

        ResponseEntity response = handlerControllerExceptions.modelExceptionHandler(modelException);

        Assertions.assertNotNull(response);
    }

    @Test
    void modelExceptionHandlerNullTest(){

        ModelException modelException = new ModelException(null, null);

        Mockito.when(statusCodeConfig.of(modelException.getError()))
                .thenReturn(new StatusCode(StatusCode.Level.FAIL, StatusCode.Level.FAIL.value(), modelException.getMessage(), "code"));

        Assertions
                .assertThrows(NullPointerException.class, () -> {
                    handlerControllerExceptions.modelExceptionHandler(modelException);
                }, "NullPointerException error was expected");
    }

    @Test
    void exceptionHandlerTest(){

        Throwable t = new Exception();
        Assertions
                .assertThrows(NullPointerException.class, () -> {
                    handlerControllerExceptions.exceptionHandler(t);
                }, "NullPointerException error was expected");

    }
    @Test
    void getMessageTest(){

        StatusCode statusCode = new StatusCode(StatusCode.Level.SUCCESS,
                "code", "message", "extCode");

        String response = handlerControllerExceptions.getMessage(statusCode, "", "");

        Assertions.assertNotNull(response);
    }

    /*
    @Test
    void methodArgumentNotValidExceptionHandlerTest(){

        MethodArgumentNotValidException e = Mockito.mock(MethodArgumentNotValidException.class);

        Mockito.when(e.getMessage()).thenReturn("error");

        Assertions
                .assertThrows(NullPointerException.class, () -> {
                    handlerControllerExceptions.methodArgumentNotValidExceptionHandler(e);
                }, "NullPointerException error was expected");
    }

    @Test
    void methodArgumentNotValidExceptionHandlerNullTest(){

        MethodArgumentNotValidException e = Mockito.mock(MethodArgumentNotValidException.class);

        Assertions
                .assertThrows(NullPointerException.class, () -> {
                    handlerControllerExceptions.methodArgumentNotValidExceptionHandler(e);
                }, "NullPointerException error was expected");
    }
*/
}
