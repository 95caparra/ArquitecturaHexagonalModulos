package co.com.movii.model.device;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInformation {

    private String deviceId;
    private Boolean isFingerCompatible;
    private Boolean isGPSCompatible;
    private String osName;
    private String osVersion;
    private String apiLevel;
    private String latitude;
    private String longitude;
    private String deviceName;
    private String deviceManufacturer;
    private String deviceResolution;
    private Boolean isRooted;
    private Boolean isJailbroken;

}
