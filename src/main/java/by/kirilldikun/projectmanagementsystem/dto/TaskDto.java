package by.kirilldikun.projectmanagementsystem.dto;

import by.kirilldikun.projectmanagementsystem.entity.Status;
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
public class TaskDto {

    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private LocalDate startAt;

    @NotNull
    private LocalDate endAt;

    @NotNull
    private Status status;

    @NotNull
    private Long projectId;
}
