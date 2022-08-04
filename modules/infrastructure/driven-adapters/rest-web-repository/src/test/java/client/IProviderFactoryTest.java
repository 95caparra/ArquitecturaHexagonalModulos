package client;

import co.com.movii.client.IProviderFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class IProviderFactoryTest {

    @Mock
    IProviderFactory iProviderFactory;


    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);

    }


    @Test
    void IProviderFactoryTest() {
        Assertions.assertDoesNotThrow( () -> {
            iProviderFactory.getProviderProperties();
        });
    }
}
