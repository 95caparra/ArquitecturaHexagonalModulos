package co.com.movii.device;

import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;
import co.com.movii.ports.in.ValidateDeviceInPort;
import co.com.movii.ports.out.ValidateDeviceOutPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidateDeviceService implements ValidateDeviceInPort {
    private final ValidateDeviceOutPort validateDeviceOutPort;

    @Override
    public ResponseDevice validateDevice(DeviceInformation deviceInformation, String phoneNumber) throws DeviceValidatorException {
        return validateDeviceOutPort.validateDevice(deviceInformation, phoneNumber);
    }
}
