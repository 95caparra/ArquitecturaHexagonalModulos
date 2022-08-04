package co.com.movii.ports.in;

import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;

public interface ValidateDeviceInPort {

    ResponseDevice validateDevice(DeviceInformation deviceInformation, String phoneNumbe) throws DeviceValidatorException;
}
