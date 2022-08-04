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
@Document(collection = "black_list")
public class BlackListEntity {

    @Id
    private String idBlackListEntity = UUID.randomUUID().toString();
    private String registryDeviceId;
    private String deviceId;
    private String deviceHash;
    private String accountId;
    private String updateDate;
    private String registryDate;
    private String signature;
    private Boolean isAltered;
}
