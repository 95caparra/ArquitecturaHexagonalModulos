package co.com.movii.repository;

import co.com.movii.entities.DeviceInformationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceInformationRepository extends MongoRepository<DeviceInformationEntity, String>{

    DeviceInformationEntity findByDeviceId(String deviceId);
}
