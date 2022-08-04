package co.com.movii.repository;

import co.com.movii.entities.DeviceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends MongoRepository<DeviceEntity, String>{

    DeviceEntity findByDeviceIdOrderByIdDeviceEntity(String deviceId);

    DeviceEntity findByIdDeviceEntity(String id);

}
