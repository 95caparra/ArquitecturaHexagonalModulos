package co.com.movii.providers.otp;

import co.com.movii.client.HttpRequest;
import co.com.movii.client.IProviderConnector;
import co.com.movii.client.ServiceException;
import co.com.movii.client.UtilsHelperClient;
import co.com.movii.model.OtpRequest;
import co.com.movii.model.exceptions.OtpException;
import co.com.movii.model.session.SessionOTP;
import co.com.movii.ports.out.OtpOutPort;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class OtpConnector extends IProviderConnector<OtpProperties, OtpFactory> implements OtpOutPort {

    public OtpConnector(@NotNull OtpProperties properties,
                             @NotNull OtpFactory factory,
                             @NotNull Environment environment) {
        super(properties, factory, environment);
    }

    @Override
    public SessionOTP generateOtp(OtpRequest request) throws OtpException {
        try {
            OtpDTO otpRequestDTO = getProviderFactory().getOtp(request);

            OtpDTO otpResponseDTO = invoke(
                    UtilsHelperClient.getCurrentCorrelative(), OtpProcess.OTP_SUPPORT.name(),
                    HttpRequest.builder()
                            .path(getProviderFactory()
                                    .getGenerateOtp().concat(request.getPhoneNumber()))
                            .mediaType(MediaType.APPLICATION_JSON)
                            .body(otpRequestDTO)
                            .build(),
                    OtpDTO.class);

            if (!otpResponseDTO.getResponseCode().equalsIgnoreCase("00")) {
                throw new OtpException("OTP_SUPPORT_".concat(otpResponseDTO.getResponseCode()), otpResponseDTO.getResponseMessage());
            }
            return mapSuccessResponse(otpResponseDTO);
        } catch (ServiceException e) {
            throw new OtpException(e);
        }
    }

    @Override
    public SessionOTP validateOtp(OtpRequest request) throws OtpException {
        try {

            OtpDTO otpResponseDTO = invoke(
                    UtilsHelperClient.getCurrentCorrelative(), OtpProcess.OTP_SUPPORT.name(),
                    HttpRequest.builder()
                            .path(getProviderFactory().getValidateOtp().concat(request.getPhoneNumber().concat("/").concat(request.getOtp())))
                            .mediaType(MediaType.APPLICATION_JSON)
                            .build(),
                    OtpDTO.class);

            if (!otpResponseDTO.getResponseCode().equalsIgnoreCase("00")) {
                throw new OtpException("OTP_SUPPORT_".concat(otpResponseDTO.getResponseCode()), otpResponseDTO.getResponseMessage());
            }
            return mapSuccessResponse(otpResponseDTO);
        } catch (ServiceException e) {
            throw new OtpException(e);
        }
    }

    private SessionOTP mapSuccessResponse(OtpDTO response) {
        return SessionOTP.builder()
                .responseCode(response.getResponseCode())
                .responseMessage(response.getResponseMessage())
                .otp(response.getOtp())
                .build();
    }


}
