package co.com.movii.model.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private String phoneNumber;
    private String registryDeviceId;
    private Boolean isValidated;
    private Boolean isActive;
    private String registrationDateTime;
    private String signature;
    private Boolean isAltered;


}
