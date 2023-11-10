package by.kirilldikun.projectmanagementsystem.controller;

import by.kirilldikun.projectmanagementsystem.dto.ProjectDto;
import by.kirilldikun.projectmanagementsystem.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@Tag(name = "Project controller", description = "Manages projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "find all", description = "Gets all projects according to the specified parameters")
    public ResponseEntity<Page<ProjectDto>> findAll(
            @RequestParam(defaultValue = "") @Parameter(description = "Query for search") String query,
            @Parameter(description = "Setting for pagination") Pageable pageable) {
        Page<ProjectDto> projects = projectService.findAll(query, pageable);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "find by id", description = "Get project by the id")
    public ResponseEntity<ProjectDto> findById(
            @PathVariable @Parameter(description = "Project id", required = true) Long id) {
        ProjectDto projectDto = projectService.findById(id);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "save", description = "Save project and return saved project")
    public ResponseEntity<ProjectDto> save(
            @Valid @RequestBody @Parameter(description = "Project data", required = true)
            ProjectDto projectDto) {
        projectDto = projectService.save(projectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectDto);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "update", description = "Update project data by the id and return updated project")
    public ResponseEntity<ProjectDto> update(
            @PathVariable @Parameter(description = "Project id", required = true) Long id,
            @Valid @RequestBody @Parameter(description = "Project data", required = true)
            ProjectDto projectDto) {
        projectDto = projectService.update(id, projectDto);
        return ResponseEntity.ok(projectDto);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "delete", description = "Delete project by the id")
    public ResponseEntity<Void> delete(
            @PathVariable @Parameter(description = "Project id", required = true) Long id) {
        projectService.delete(id);
        return ResponseEntity.ok().build();
    }
}
