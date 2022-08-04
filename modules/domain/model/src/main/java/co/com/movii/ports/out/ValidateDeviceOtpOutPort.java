package co.com.movii.ports.out;

import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;

public interface ValidateDeviceOtpOutPort {

    ResponseDevice validateDeviceOtp(DeviceInformation deviceInformation, String phoneNumber, String otp) throws DeviceValidatorException;
}
