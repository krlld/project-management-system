package by.kirilldikun.projectmanagementsystem.controller;

import by.kirilldikun.projectmanagementsystem.dto.AuthenticationRequest;
import by.kirilldikun.projectmanagementsystem.dto.AuthenticationResponse;
import by.kirilldikun.projectmanagementsystem.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication controller", description = "Authenticate the user in the system")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/register")
    @Operation(summary = "Register", description = "Allows the user to register in the system")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody @Parameter(description = "User data", required = true)
            AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.register(authenticationRequest));
    }

    @GetMapping("/authenticate")
    @Operation(summary = "Authenticate", description = "Allows the user to authenticate in the system")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody @Parameter(description = "User data", required = true)
            AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
