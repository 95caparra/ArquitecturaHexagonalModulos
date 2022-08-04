package co.com.movii.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "white_list")
public class WhiteListEntity {

    @Id
    private String idWhiteListEntity = UUID.randomUUID().toString();
    private String registryDeviceId;
    private String deviceId;
    private String deviceHash;
    private String accountId;
    private String updateDate;
    private String registryDate;
    private String signature;
    private Boolean isAltered;
}
