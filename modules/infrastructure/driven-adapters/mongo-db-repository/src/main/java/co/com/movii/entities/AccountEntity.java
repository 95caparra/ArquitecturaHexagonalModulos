package co.com.movii.entities;

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
@Document(collection = "account")
public class AccountEntity {

    @Id
    private String idAccountEntity = UUID.randomUUID().toString();
    private String phoneNumber;
    private String registryDeviceId;
    private Boolean isValidated;
    private Boolean isActive;
    private LocalDateTime registrationDateTime;
    private String signature;
    private Boolean isAltered;
}
