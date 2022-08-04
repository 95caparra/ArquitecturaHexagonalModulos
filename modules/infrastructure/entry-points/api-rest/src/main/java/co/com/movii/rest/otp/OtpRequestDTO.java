package co.com.movii.rest.otp;

import co.com.movii.rest.common.ResponseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OtpRequestDTO extends ResponseCommand {
    private String phoneNumber;
    private String email;
}
