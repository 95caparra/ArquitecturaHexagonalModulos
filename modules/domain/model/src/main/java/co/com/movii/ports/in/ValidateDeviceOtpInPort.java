package co.com.movii.ports.in;

import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;

public interface ValidateDeviceOtpInPort {

    ResponseDevice validateDeviceOtp(DeviceInformation deviceInformation, String phoneNumber, String otp) throws DeviceValidatorException;
}
