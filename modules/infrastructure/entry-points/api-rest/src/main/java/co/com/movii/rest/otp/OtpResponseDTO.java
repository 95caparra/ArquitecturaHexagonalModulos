package co.com.movii.rest.otp;

import co.com.movii.model.session.SessionOTP;
import co.com.movii.rest.common.ResponseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class OtpResponseDTO extends ResponseCommand {

    @Schema(description = "Datos de respuesta del servicio")
    private SessionOTP data;
}
