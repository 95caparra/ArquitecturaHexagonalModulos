package co.com.movii.model.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    private String deviceHash;
    private String registrationDateTime;
    private String updateDateTime;
    private Boolean isValidated;
    private String deviceId;
    private DeviceInformation deviceInformation;
    private String signature;
    private Boolean isAltered;

}
