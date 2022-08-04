package co.com.movii.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpRequest {

    @Builder.Default
    private MediaType mediaType = MediaType.APPLICATION_JSON;
    @Builder.Default
    private HttpMethod httpMethod = HttpMethod.POST;
    @Builder.Default
    private String path = ConstantsHelper.EMPTY_STRING;
    @Builder.Default
    private IModel body = new IModel.DefaultModel();
    @Builder.Default
    private Map<String, String> headers = new HashMap<>();
    @Builder.Default
    private List<String> urlValuesToProtect = new ArrayList<>();

    public final String getProtectPath() {
        return urlValuesToProtect.stream()
                .reduce(path, (accumulativePath, value) -> {
                    var protectedValue = StringUtils.repeat(ConstantsHelper.ASTERISK, value.length());

                    accumulativePath = accumulativePath.replaceAll(
                            ConstantsHelper.PATH_PARAMETERS_SEPARATOR.concat(value),
                            ConstantsHelper.PATH_PARAMETERS_SEPARATOR.concat(protectedValue));
                    accumulativePath = accumulativePath.replaceAll(ConstantsHelper.EQUALS_SIGN.concat(value),
                            ConstantsHelper.EQUALS_SIGN.concat(protectedValue));
                    accumulativePath = accumulativePath
                            .replaceAll(ConstantsHelper.URL_PARAMETERS_SEPARATOR.concat(value),
                            ConstantsHelper.URL_PARAMETERS_SEPARATOR.concat(protectedValue));

                    return accumulativePath;
                });
    }
}