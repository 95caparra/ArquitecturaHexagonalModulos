package co.com.movii.providers.otp;

import co.com.movii.client.IProviderProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "providers.pathGenerate")
public class PathGenerateProperties extends IProviderProperties {
}
