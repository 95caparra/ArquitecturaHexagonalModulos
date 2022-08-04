package co.com.movii.repository;

import co.com.movii.entities.WhiteListEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WhiteListRepository extends MongoRepository<WhiteListEntity, String> {

    WhiteListEntity findByRegistryDeviceIdAndDeviceHash(String deviceId, String deviceHash);
}
