package co.com.movii.rest.common;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;

public enum StatusCodes {

    SERVER_ERROR(Exception.class),
    BAD_REQUEST(MethodArgumentNotValidException.class);

    private final Class<?> exceptionClass;

    StatusCodes(Class<?> exceptionClazz) {
        this.exceptionClass = exceptionClazz;
    }

    public static StatusCodes getByClass(Class<?> clazz) {
        return Arrays.stream(StatusCodes.values())
                .filter(statusCode -> statusCode.exceptionClass.getCanonicalName()
                        .equals(clazz.getCanonicalName()))
                .findFirst()
                .orElse(SERVER_ERROR);
    }
}
