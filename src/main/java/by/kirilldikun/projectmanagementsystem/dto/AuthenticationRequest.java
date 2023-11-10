package by.kirilldikun.projectmanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "User data for authentication")
public record AuthenticationRequest(
        @NotNull @NotBlank @Schema(description = "Username") String username,
        @NotNull @NotBlank @Schema(description = "Password") String password) {
}
