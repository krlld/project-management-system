package by.kirilldikun.projectmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticationRequest(
        @NotNull @NotBlank String username,
        @NotNull @NotBlank String password) {
}