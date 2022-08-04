package co.com.movii.ports.out;

import co.com.movii.model.OtpRequest;
import co.com.movii.model.exceptions.OtpException;
import co.com.movii.model.session.SessionOTP;

public interface OtpOutPort {

    SessionOTP generateOtp(OtpRequest request) throws OtpException;

    SessionOTP validateOtp(OtpRequest request) throws OtpException;
}
