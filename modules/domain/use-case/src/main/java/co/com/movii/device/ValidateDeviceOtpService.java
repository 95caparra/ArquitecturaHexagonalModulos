package co.com.movii.device;

import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;
import co.com.movii.ports.in.ValidateDeviceOtpInPort;
import co.com.movii.ports.out.ValidateDeviceOtpOutPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateDeviceOtpService implements ValidateDeviceOtpInPort {

    private final ValidateDeviceOtpOutPort validateDeviceOtpOutPort;

    @Override
    public ResponseDevice validateDeviceOtp(DeviceInformation deviceInformation, String phoneNumber, String otp) throws DeviceValidatorException {
        return validateDeviceOtpOutPort.validateDeviceOtp(deviceInformation, phoneNumber, otp);
    }
}
