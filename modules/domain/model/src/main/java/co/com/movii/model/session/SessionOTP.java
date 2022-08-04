package co.com.movii.model.session;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class SessionOTP {

    private String responseCode;
    private String responseMessage;
    private String otp;
}
