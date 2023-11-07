package by.kirilldikun.projectmanagementsystem.controller;

import by.kirilldikun.projectmanagementsystem.dto.ProjectDto;
import by.kirilldikun.projectmanagementsystem.service.ProjectService;
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
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<Page<ProjectDto>> findAll(
            @RequestParam(defaultValue = "") String query,
            Pageable pageable) {
        Page<ProjectDto> projects = projectService.findAll(query, pageable);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> findById(@PathVariable Long id) {
        ProjectDto projectDto = projectService.findById(id);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> save(@Valid @RequestBody ProjectDto projectDto) {
        projectDto = projectService.save(projectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> update(
            @PathVariable Long id,
            @Valid @RequestBody ProjectDto projectDto) {
        projectDto = projectService.update(id, projectDto);
        return ResponseEntity.ok(projectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.ok().build();
    }
}
