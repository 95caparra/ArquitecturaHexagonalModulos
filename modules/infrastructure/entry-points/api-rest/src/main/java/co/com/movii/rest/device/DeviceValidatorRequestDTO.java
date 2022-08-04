package co.com.movii.rest.device;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class DeviceValidatorRequestDTO {

    private String deviceId;
    private String deviceName;
    private String deviceManufacturer;
    private String deviceResolution;
    private Boolean isRooted;
    private String osName;
    private String osVersion;
    private String apiLevel;
    private String latitude;
    private String longitude;
    private Boolean isJailbroken;
    private Boolean isFingerCompatible;
    private Boolean isGPSCompatible;

}
