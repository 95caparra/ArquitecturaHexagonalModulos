package co.com.movii.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@Data
@ConfigurationProperties(prefix = "providers")
public abstract class IProviderProperties implements Serializable {

    private String name;
    private String url;
    private int connectionTimeout;
    private int readTimeout;
}
