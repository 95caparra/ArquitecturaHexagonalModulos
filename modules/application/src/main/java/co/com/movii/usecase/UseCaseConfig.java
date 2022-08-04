package co.com.movii.usecase;

import co.com.movii.device.ValidateDeviceOtpService;
import co.com.movii.device.ValidateDeviceService;
import co.com.movii.device.RegistryDeviceService;
import co.com.movii.otp.OtpService;
import co.com.movii.ports.in.*;
import co.com.movii.ports.out.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public RegistryDeviceInPort registryDeviceUseCase(RegistryDeviceOutPort registryDeviceOutPort){
        return new RegistryDeviceService(registryDeviceOutPort);
    }

    @Bean
    public ValidateDeviceInPort validateDeviceUseCase(ValidateDeviceOutPort validateDeviceOutPort){
        return new ValidateDeviceService(validateDeviceOutPort);
    }

    @Bean
    public ValidateDeviceOtpInPort validateDeviceOtpUseCase(ValidateDeviceOtpOutPort validateDeviceOtpOutPort){
        return new ValidateDeviceOtpService(validateDeviceOtpOutPort);
    }

    @Bean
    public OtpInPort otpUseCase(OtpOutPort otpOutPort) {
        return new OtpService(otpOutPort);
    }




}
