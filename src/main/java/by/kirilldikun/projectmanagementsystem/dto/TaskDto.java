package by.kirilldikun.projectmanagementsystem.dto;

import by.kirilldikun.projectmanagementsystem.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Task data")
public class TaskDto {

    @Schema(description = "Task id", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @NotNull
    @NotBlank
    @Schema(description = "Task name", example = "Design Mockups")
    private String name;

    @NotNull
    @NotBlank
    @Schema(description = "Task description",
            example = "Create UI mockups based on client requirements")
    private String description;

    @NotNull
    @Schema(description = "Task start date", example = "2023-01-10")
    private LocalDate startAt;

    @NotNull
    @Schema(description = "Task end date", example = "2023-01-20")
    private LocalDate endAt;

    @NotNull
    @Schema(description = "Task status", example = "IN_PROGRESS")
    private Status status;

    @NotNull
    @Schema(description = "Project id", example = "1")
    private Long projectId;
}
