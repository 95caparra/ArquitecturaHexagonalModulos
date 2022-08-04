package co.com.movii.rest.otp;

import co.com.movii.model.OtpRequest;
import co.com.movii.model.exceptions.OtpException;
import co.com.movii.model.session.SessionOTP;
import co.com.movii.ports.in.OtpInPort;
import co.com.movii.rest.common.HandlerControllerExceptions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "otp", description = "OTP API")
@RequestMapping("/otp")
public class OtpController extends HandlerControllerExceptions {

    private final OtpInPort otpInPort;

    @PostMapping
    @Operation(summary = "login users", description = "login users movii", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Message not found")
    }
    )
    public OtpResponseDTO otp(@Parameter(description = "RequestBody", required = true) @RequestBody OtpRequestDTO request) throws OtpException {
        SessionOTP session = otpInPort.generateOtp(OtpRequest.builder()
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build());

        return OtpResponseDTO.builder()
                .data(session)
                .build();
    }
}
