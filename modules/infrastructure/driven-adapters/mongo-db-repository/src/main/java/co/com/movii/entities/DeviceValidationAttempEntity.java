package co.com.movii.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "device_validation_attemps")
public class DeviceValidationAttempEntity {

    @Id
    private String idDeviceValidationAttempEntity = UUID.randomUUID().toString();
    private String registryDeviceId;
    private String deviceId;
    private String deviceHash;
    private String accountId;
    private String validationStatus;
    private LocalDateTime validationDateTime;
    private String signature;
    private Boolean isAltered;
}
