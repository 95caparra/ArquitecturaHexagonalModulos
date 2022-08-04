package co.com.movii.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpRequest {
    private String phoneNumber;
    private String email;
    private String otp;
}
