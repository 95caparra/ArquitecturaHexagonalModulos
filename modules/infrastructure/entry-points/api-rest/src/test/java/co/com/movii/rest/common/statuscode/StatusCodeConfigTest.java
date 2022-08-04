package co.com.movii.rest.common.statuscode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Map;

class StatusCodeConfigTest {

    @InjectMocks
    StatusCodeConfig statusCodeConfig;

    @Mock
    Map<String, StatusCodeConfig.StatusCodeConfigItem> success;

    @Mock
    StatusCodeConfig.StatusCodeConfigItem statusCodeConfigItem;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void ofTest(){
        StatusCode result =  statusCodeConfig.of("00");
        Assertions.assertNotNull(result);
    }
}
