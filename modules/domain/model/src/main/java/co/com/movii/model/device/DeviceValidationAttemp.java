package co.com.movii.model.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceValidationAttemp {

    private String registryDeviceId;
    private String deviceId;
    private String deviceHash;
    private String accountId;
    private String validationStatus;
    private String validationDateTime;
    private String signature;
    private Boolean isAltered;

}
