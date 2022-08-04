package client;

import co.com.movii.client.HttpRequest;
import co.com.movii.client.IModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class HttpRequestTest {
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProtectPathTest(){
        // Arrange
        List<String> urlValuesToProtect = new ArrayList<>();
        urlValuesToProtect.add("");

        var httpRequest = HttpRequest.builder()
                .path("/test")
                .body(new ModelMock())
                .urlValuesToProtect(urlValuesToProtect)
                .build();

        //Act
        String result = httpRequest.getProtectPath();

        //Asserts
        Assertions.assertNotNull(result);

    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ModelMock extends IModel {
        private String code;
    }


}
