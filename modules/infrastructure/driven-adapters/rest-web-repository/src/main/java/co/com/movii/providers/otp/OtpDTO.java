package co.com.movii.providers.otp;

import co.com.movii.client.IModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ConfigurationProperties(prefix = "providers")
public class OtpDTO extends IModel {

    @JsonProperty("email")
    private String email;

    @JsonProperty("otpLength")
    private Integer otpLength;

    @JsonProperty("otpExpiration")
    private Integer otpExpiration;

    @JsonProperty("otpAlphanumeric")
    private Boolean otpAlphanumeric;

    @JsonProperty("templateCode")
    private String templateCode;

    @JsonProperty("sendSms")
    private Boolean sendSms;

    @JsonProperty("responseCode")
    private String responseCode;

    @JsonProperty("responseMessage")
    private String responseMessage;

    @JsonProperty("otp")
    private String otp;

    @JsonProperty("variables")
    private VariablesDTO variables;

    @JsonProperty("url")
    private String url;

}
