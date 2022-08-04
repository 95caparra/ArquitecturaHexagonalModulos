package co.com.movii.repository;

import co.com.movii.entities.BlackListEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlackListRepository  extends MongoRepository<BlackListEntity, String> {

    BlackListEntity findByRegistryDeviceIdAndDeviceHash(String deviceId, String deviceHash);
}
