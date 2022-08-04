package co.com.movii.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "device_information")
public class DeviceInformationEntity implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();
    @Field(name = "deviceId")
    private String deviceId;
    @Field(name = "deviceName")
    private String deviceName;
    @Field(name = "deviceManufacturer")
    private String deviceManufacturer;
    @Field(name = "deviceResolution")
    private String deviceResolution;
    @Field(name = "isRooted")
    private Boolean isRooted;
    @Field(name = "isJailbroken")
    private Boolean isJailbroken;
    @Field(name = "isFingerCompatible")
    private Boolean isFingerCompatible;
    @Field(name = "isGPSCompatible")
    private Boolean isGPSCompatible;
    @Field(name = "osName")
    private String osName;
    @Field(name = "osVersion")
    private String osVersion;
    @Field(name = "apiLevel")
    private String apiLevel;
    @Field(name = "latitude")
    private String latitude;
    @Field(name = "longitude")
    private String longitude;

}
