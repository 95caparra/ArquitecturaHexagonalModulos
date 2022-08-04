package co.com.movii.rest.device.validateotp;

import co.com.movii.mapper.DeviceInfoMapper;
import co.com.movii.model.device.DeviceInformation;
import co.com.movii.model.device.ResponseDevice;
import co.com.movii.model.exceptions.DeviceValidatorException;
import co.com.movii.ports.in.ValidateDeviceOtpInPort;
import co.com.movii.rest.common.HandlerControllerExceptions;
import co.com.movii.rest.device.DeviceValidatorRequestDTO;
import co.com.movii.rest.device.DeviceValidatorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("validate-device-otp")
public class DeviceValidatorOtpController extends HandlerControllerExceptions {

    private final ValidateDeviceOtpInPort validateDeviceOtpInPortInPort;

    @PostMapping
    @Operation(summary = "registry device", description = "registry device", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Message not found")
    }
    )
    public DeviceValidatorResponseDTO validationDeviceOtp(@Parameter(description = "RequestBody", required = true)
                                                       @RequestHeader("phoneNumber") String phoneNumber,
                                                       @RequestHeader("otp") String otp,
                                                       @RequestBody DeviceValidatorRequestDTO deviceValidatorRequestDTO) throws DeviceValidatorException {


        //Mapeo de Objeto de respuesta
        DeviceInformation deviceInformation = DeviceInfoMapper.INSTANCE.deviceValidatorRequestDTOToDevice(deviceValidatorRequestDTO);

        ResponseDevice responseDevice = validateDeviceOtpInPortInPort.validateDeviceOtp(deviceInformation, phoneNumber, otp);

        return DeviceValidatorResponseDTO.builder()
                .data(responseDevice)
                .build();
    }
}
