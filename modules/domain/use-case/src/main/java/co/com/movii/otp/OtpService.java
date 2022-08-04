package co.com.movii.otp;

import co.com.movii.model.OtpRequest;
import co.com.movii.model.exceptions.OtpException;
import co.com.movii.model.session.SessionOTP;
import co.com.movii.ports.in.OtpInPort;
import co.com.movii.ports.out.OtpOutPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OtpService implements OtpInPort {

    private final OtpOutPort otpOutPort;

    @Override
    public SessionOTP generateOtp(OtpRequest request) throws OtpException {
        return otpOutPort.generateOtp(request);
    }

    @Override
    public SessionOTP validateOtp(OtpRequest request) throws OtpException {
        return otpOutPort.validateOtp(request);
    }
}
