package co.com.movii.entities;

import co.com.movii.model.device.DeviceInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "device")
public class DeviceEntity implements Serializable {

    @Id
    private String idDeviceEntity = UUID.randomUUID().toString();
    private String deviceHash;
    private LocalDateTime registrationDateTime;
    private LocalDateTime updateDateTime;
    private Boolean isValidated;
    private String deviceId;
    private DeviceInformation deviceInformation;
    private String signature;
    private Boolean isAltered;
}
