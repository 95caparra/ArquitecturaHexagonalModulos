package co.com.movii.ports.in;

import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;

public interface RegistryDeviceInPort {

    ResponseDevice registryDevice(DeviceInformation deviceInformation, String phoneNumber) throws DeviceValidatorException;

}
