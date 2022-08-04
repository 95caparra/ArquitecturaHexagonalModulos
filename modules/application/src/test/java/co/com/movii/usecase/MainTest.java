package co.com.movii.usecase;

import co.com.movii.MainApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

class MainTest {

    @InjectMocks
    MainApplication mainApplication;

    @Mock
    Environment environment;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void main() {
        String [] args = { "one", "two", "three" };

        Assertions.assertDoesNotThrow( () -> {
            mainApplication.main(args);
        });

    }

}
