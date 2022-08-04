package co.com.movii.rest.device;

import co.com.movii.model.device.ResponseDevice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceValidatorResponseDTO {

    @Schema(description = "Datos de respuesta del servicio")
    private ResponseDevice data;
}
