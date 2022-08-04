package co.com.movii.mapper;

import co.com.movii.model.device.DeviceInformation;
import co.com.movii.rest.device.DeviceValidatorRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeviceInfoMapper {


    DeviceInfoMapper INSTANCE = Mappers.getMapper(DeviceInfoMapper.class);

    DeviceInformation deviceValidatorRequestDTOToDevice(DeviceValidatorRequestDTO deviceValidatorRequestDTO);

}
