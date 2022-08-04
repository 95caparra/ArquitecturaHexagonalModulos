package co.com.movii.model.exceptions;

public class OtpException extends ModelException {

    public OtpException(String errorI, String defaultMessageI) {
        super(errorI, defaultMessageI);
    }

    public OtpException(Throwable e) {
        super(e);
    }

}
