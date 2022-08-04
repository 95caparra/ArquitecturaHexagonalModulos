package co.com.movii.client;

import lombok.Data;

@Data
public abstract class IProviderFactory<T> {

    private T providerProperties;

    protected IProviderFactory(T providerPropertiesI) {
        this.providerProperties = providerPropertiesI;
    }
}
