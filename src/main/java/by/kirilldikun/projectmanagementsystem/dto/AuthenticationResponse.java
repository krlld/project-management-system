package by.kirilldikun.projectmanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication response with token")
public record AuthenticationResponse(@Schema(description = "Authentication token") String token) {
}
