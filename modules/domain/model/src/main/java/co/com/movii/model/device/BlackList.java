package co.com.movii.model.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlackList {

    private String registryDeviceId;
    private String deviceId;
    private String deviceHash;
    private String accountId;
    private String updateDate;
    private String registryDate;
    private String signature;
    private Boolean isAltered;
}
