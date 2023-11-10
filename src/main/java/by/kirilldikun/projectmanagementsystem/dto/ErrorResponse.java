package by.kirilldikun.projectmanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Error response")
public record ErrorResponse(@Schema(description = "Error massage") String message) {
}
