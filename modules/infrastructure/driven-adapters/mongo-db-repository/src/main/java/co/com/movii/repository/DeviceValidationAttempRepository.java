package co.com.movii.repository;

import co.com.movii.entities.DeviceValidationAttempEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceValidationAttempRepository extends MongoRepository<DeviceValidationAttempEntity, String> {
}
