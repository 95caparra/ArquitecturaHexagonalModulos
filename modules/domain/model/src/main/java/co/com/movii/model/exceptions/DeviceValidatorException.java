package co.com.movii.model.exceptions;

public class DeviceValidatorException extends ModelException {

    public DeviceValidatorException(String errorI, String defaultMessageI) {
        super(errorI, defaultMessageI);
    }

    public DeviceValidatorException(Throwable e) {
        super(e);
    }
}
