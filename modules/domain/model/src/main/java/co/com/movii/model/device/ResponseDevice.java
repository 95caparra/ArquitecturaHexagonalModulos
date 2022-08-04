package co.com.movii.model.device;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ResponseDevice {

    private String responseCode;
    private String responseMessage;
}
