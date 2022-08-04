package client;

import co.com.movii.client.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import okhttp3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

class IProviderConnectorTest {

    @Mock
    private ProviderPropertiesMock providerProperties;

    @Mock
    private ProviderFactoryMock providerFactory;

    @Mock
    private Environment environment;

    @Mock
    private OkHttpClient okHttpClient;

    @Mock
    private Exception exception;

    @Mock
    private ServiceException serviceException;

    private ProviderConnectorMock providerConnector;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        providerConnector = new ProviderConnectorMock(providerProperties, providerFactory, environment, okHttpClient);
    }

    @Test
    void canBeCreated() {
        Assertions.assertNotNull(providerConnector);
    }

    @Test
    void invokeTest() throws ServiceException, IOException {
        // Arrange
        var correlative = "123123";
        var requestIdentification = "test";
        var httpRequest = HttpRequest.builder()
                .path("/test")
                .body(new ModelMock())
                .build();

        Mockito.when(environment.getProperty("component.name")).thenReturn("test-component");
        Mockito.when(providerProperties.getName()).thenReturn("test-provider-name");
        Mockito.when(providerProperties.getUrl()).thenReturn("http://localhost:80/");
        Call call = Mockito.mock(Call.class);

        ResponseBody responseBody = ResponseBody
                .create("{}".getBytes(StandardCharsets.UTF_8), MediaType.get("application/json"));
        Response clientResponse = getResponseOkHttp(responseBody);

        Mockito.when(call.execute()).thenReturn(clientResponse);
        Mockito.when(okHttpClient.newCall(Mockito.any())).thenReturn(call);

        // Act
        var response = providerConnector
                .invoke(correlative, requestIdentification, httpRequest, ModelMock.class);

        // Assertions
        Assertions.assertNotNull(response);
    }

    @Test
    void invokeExceptionTest() throws ServiceException, IOException {
        // Arrange
        var correlative = "123123";
        var requestIdentification = "test";
        var httpRequest = HttpRequest.builder()
                .path("/test")
                .body(new ModelMock())
                .build();

        Mockito.when(environment.getProperty("component.name")).thenReturn("test-component");
        Mockito.when(providerProperties.getName()).thenReturn("test-provider-name");
        Mockito.when(providerProperties.getUrl()).thenReturn("http://localhost:80/");
        Call call = Mockito.mock(Call.class);

        ResponseBody responseBody = ResponseBody
                .create("{}".getBytes(StandardCharsets.UTF_8), MediaType.get("application/json"));
        Response clientResponse = getResponseOkHttp(responseBody);

        Mockito.when(call.execute()).thenReturn(clientResponse);
        Mockito.when(okHttpClient.newCall(Mockito.any())).thenReturn(call);




        Throwable cause = new Throwable("error");
        Exception ex = new Exception("message", cause);



        Mockito.when(providerConnector
                        .invoke(correlative, requestIdentification, httpRequest, ModelMock.class, false))
                .thenCallRealMethod();



        // Act y Assertions

        Assertions.assertThrows(ServiceException.class,  () -> {
            providerConnector
                    .invoke(correlative, requestIdentification, httpRequest, ModelMock.class);
        }, "ServiceException error was expected");
    }

    public Response getResponseOkHttp(ResponseBody responseBody){
        return new Response(
                new Request(new HttpUrl("http", "", "", "localhost",
                        80, List.of("a"), null, null, ""),
                        "", Headers.of(), null, new HashMap<>()),
                Protocol.HTTP_1_0, "", 0, null, Headers.of(), responseBody,
                null, null, null,
                0, 0, null);
    }

    public static class ProviderConnectorMock extends IProviderConnector<ProviderPropertiesMock, ProviderFactoryMock> {

        protected ProviderConnectorMock(ProviderPropertiesMock properties, ProviderFactoryMock factory,
                                        Environment environment, OkHttpClient client) {
            super(properties, factory, environment, client);
        }
    }

    public static class ProviderPropertiesMock extends IProviderProperties {

    }

    public static class ProviderFactoryMock extends IProviderFactory<ProviderPropertiesMock> {

        protected ProviderFactoryMock(ProviderPropertiesMock providerPropertiesI) {
            super(providerPropertiesI);
        }
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ModelMock extends IModel {
        private String code;
    }
}
