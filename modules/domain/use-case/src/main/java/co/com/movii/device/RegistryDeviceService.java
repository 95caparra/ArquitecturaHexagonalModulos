package co.com.movii.device;

import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;
import co.com.movii.ports.in.RegistryDeviceInPort;
import co.com.movii.ports.out.RegistryDeviceOutPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegistryDeviceService implements RegistryDeviceInPort {

    private final RegistryDeviceOutPort registryDeviceOutPort;

    @Override
    public ResponseDevice registryDevice(DeviceInformation deviceInformation, String phoneNumber) throws DeviceValidatorException {
        return registryDeviceOutPort.registryDevice(deviceInformation, phoneNumber);
    }

}
