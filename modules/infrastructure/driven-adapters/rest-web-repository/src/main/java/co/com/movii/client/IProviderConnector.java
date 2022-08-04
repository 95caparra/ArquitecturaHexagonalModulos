package co.com.movii.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * This class provide invoker for call rest services
 *
 * @param <P> is the properties class for third component
 * @param <F> is the factory class for third component
 */
@Data
@Slf4j
public abstract class IProviderConnector<P extends IProviderProperties, F extends IProviderFactory<P>> {

    private final P providerProperties;
    private final F providerFactory;
    private final OkHttpClient client;
    private final Environment environment;

    private static final Long TIMEOUT_VALUE = 50L;

    /**
     * @param properties properties for third component
     * @param factory    factory for create bodies for call third component
     */
    protected IProviderConnector(@NotNull P properties,
                                 @NotNull F factory,
                                 @NotNull Environment environment) {
        this.providerProperties = properties;
        this.providerFactory = factory;
        this.environment = environment;
        this.client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
                .build();
    }

    protected IProviderConnector(@NotNull P properties,
                                 @NotNull F factory,
                                 @NotNull Environment environment,
                                 @NotNull OkHttpClient client) {
        this.providerProperties = properties;
        this.providerFactory = factory;
        this.environment = environment;
        this.client = client;
    }

    /**
     * Invoke services
     *
     * @param correlationId             correlation id
     * @param requestIdentificationName request identifier
     * @param httpRequest               request
     * @param responseClass             response class
     * @param logsControl               print logs controls
     * @param <T>                       response type
     * @return response of service
     * @throws ServiceException error of requests
     */
    public <T> T invoke(String correlationId, String requestIdentificationName, HttpRequest httpRequest,
                        Class<T> responseClass, boolean... logsControl) throws ServiceException {
        try {
            UtilsHelperClient.assignCorrelative(environment.getProperty("component.name", ""), correlationId);
            showLogsRequest(httpRequest.getProtectPath(), httpRequest.getBody(), requestIdentificationName, logsControl);

            var body = (httpRequest.getMediaType().equals(MediaType.APPLICATION_XML))
                    ? httpRequest.getBody().toXml()
                    : httpRequest.getBody().toJson();
            var mediaType = okhttp3.MediaType.get(httpRequest.getMediaType().toString());


            var request = new Request.Builder()
                    .url(providerProperties.getUrl().concat(httpRequest.getPath()))
                    .method(httpRequest.getHttpMethod().name(), RequestBody.create(mediaType, body))
                    .headers(Headers.of(httpRequest.getHeaders()))
                    .build();

            var call = client.newCall(request);
            var response = call.execute();

            var responseString = "";
            if (response.body() != null) {
                responseString = response.body().string();
            }

            return getResponse(correlationId, httpRequest, requestIdentificationName, responseString, responseClass);
        } catch (Exception error) {
            throw new ServiceException(error);
        }
    }

    public <T> T getResponse(String correlationId, HttpRequest httpRequest, String identification,
                             String stringResponse, Class<T> responseClass, boolean... logsControl)
            throws JsonProcessingException {
        UtilsHelperClient.assignCorrelative(environment.getProperty("component.name", ""), correlationId);
        var objectMapper = (httpRequest.getMediaType().equals(MediaType.APPLICATION_XML))
                ? UtilsHelperClient.getXML_MAPPER() : UtilsHelperClient.getJSON_MAPPER();
        var response = objectMapper.readValue(stringResponse, responseClass);

        if (logsControl.length <= 1 || logsControl[1]) {
            log.info(ConstantsHelper.LBL_RESPONSE, getProviderProperties().getName().toUpperCase(), identification,
                    response instanceof IModel ? ((IModel) response).protectedToString() : response);
        } else {
            log.debug(ConstantsHelper.LBL_RESPONSE, getProviderProperties().getName().toUpperCase(), identification,
                    response instanceof IModel ? ((IModel) response).protectedToString() : response);
        }

        return response;
    }

    public void showLogsRequest(String path, IModel body, String identification, boolean... logsControl) {
        if (logsControl.length <= 0 || logsControl[0]) {
            log.info(ConstantsHelper.LOG_THIRD_REQUEST, getProviderProperties().getName().toUpperCase(),
                    identification, providerProperties.getUrl() + path, body.protectedToString());
        } else {
            log.debug(ConstantsHelper.LOG_THIRD_REQUEST, getProviderProperties().getName().toUpperCase(),
                    identification, providerProperties.getUrl() + path, body.protectedToString());
        }
    }

}