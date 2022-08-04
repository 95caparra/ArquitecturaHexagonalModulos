package co.com.movii.mapper;

import co.com.movii.entities.AccountEntity;
import co.com.movii.entities.DeviceEntity;
import co.com.movii.entities.DeviceInformationEntity;
import co.com.movii.entities.DeviceValidationAttempEntity;
import co.com.movii.model.device.Account;
import co.com.movii.model.device.Device;
import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.DeviceValidationAttemp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeviceInformationMapper {

    DeviceInformationMapper INSTANCE = Mappers.getMapper(DeviceInformationMapper.class);

    DeviceInformationEntity deviceInformationToDeviceInformationEntity(DeviceInformation deviceInformation);

    DeviceEntity deviceToDeviceEntity(Device device);

    DeviceValidationAttempEntity deviceValidationAttempToDeviceValidationAttempEntity(DeviceValidationAttemp deviceValidationAttemp);

    AccountEntity accountToAccountEntity(Account account);
}
