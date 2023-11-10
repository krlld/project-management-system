package by.kirilldikun.projectmanagementsystem.dto;

import by.kirilldikun.projectmanagementsystem.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Project data")
public class ProjectDto {

    @Schema(description = "Project id", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @NotNull
    @NotBlank
    @Schema(description = "Project name", example = "Project Alpha")
    private String name;

    @NotNull
    @NotBlank
    @Schema(description = "Project description",
            example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
    private String description;

    @NotNull
    @Positive
    @Schema(description = "Project budget", example = "50000.00")
    private BigDecimal budget;

    @NotNull
    @Schema(description = "Project start date", example = "2023-01-01")
    private LocalDate startAt;

    @NotNull
    @Schema(description = "Project end date", example = "2023-03-15")
    private LocalDate endAt;

    @NotNull
    @Schema(description = "Project status", example = "IN_PROGRESS")
    private Status status;
}
