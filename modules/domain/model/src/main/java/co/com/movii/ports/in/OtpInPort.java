package co.com.movii.ports.in;

import co.com.movii.model.OtpRequest;
import co.com.movii.model.exceptions.OtpException;
import co.com.movii.model.session.SessionOTP;

public interface OtpInPort {

    SessionOTP generateOtp(OtpRequest request) throws OtpException;

    SessionOTP validateOtp(OtpRequest request) throws OtpException;
}
