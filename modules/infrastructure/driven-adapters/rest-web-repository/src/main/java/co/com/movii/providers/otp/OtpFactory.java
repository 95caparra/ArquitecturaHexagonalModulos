package co.com.movii.providers.otp;

import co.com.movii.client.IProviderFactory;
import co.com.movii.model.OtpRequest;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Component
@EqualsAndHashCode(callSuper = false)
public class OtpFactory extends IProviderFactory<OtpProperties> {

    public OtpFactory(
            OtpProperties otpProperties
    ) {
        super(otpProperties);
    }

    public OtpDTO getOtp(OtpRequest request) {
        return getProviderProperties().getOtp().toBuilder()
                .email(request.getEmail())
                .build();
    }

    public String getValidateOtp() {
        return getProviderProperties().getPathValidate();
    }

    public String getGenerateOtp() {
        return getProviderProperties().getPathGenerate();
    }
}
