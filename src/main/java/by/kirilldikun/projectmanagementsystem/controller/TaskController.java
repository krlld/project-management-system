package by.kirilldikun.projectmanagementsystem.controller;

import by.kirilldikun.projectmanagementsystem.dto.TaskDto;
import by.kirilldikun.projectmanagementsystem.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/task")
@RequiredArgsConstructor
@Tag(name = "Task controller", description = "Manages tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "find all", description = "Gets all tasks according to the specified parameters")
    public ResponseEntity<Page<TaskDto>> findAll(
            @RequestParam(defaultValue = "") @Parameter(description = "Query for search") String query,
            @Parameter(description = "Setting for pagination") Pageable pageable) {
        Page<TaskDto> tasks = taskService.findAll(query, pageable);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    @Operation(summary = "find by id", description = "Get task by the id")
    public ResponseEntity<TaskDto> findById(
            @PathVariable @Parameter(description = "Task id", required = true) Long id) {
        TaskDto taskDto = taskService.findById(id);
        return ResponseEntity.ok(taskDto);
    }

    @PostMapping
    @Operation(summary = "save", description = "Save task and return saved task")
    public ResponseEntity<TaskDto> save(
            @Valid @RequestBody @Parameter(description = "Task data", required = true)
            TaskDto taskDto) {
        taskDto = taskService.save(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update", description = "Update task data by the id and return updated task")
    public ResponseEntity<TaskDto> update(
            @PathVariable @Parameter(description = "Task id", required = true) Long id,
            @Valid @RequestBody @Parameter(description = "Task data", required = true)
            TaskDto taskDto) {
        taskDto = taskService.update(id, taskDto);
        return ResponseEntity.ok(taskDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete", description = "Delete task by the id")
    public ResponseEntity<Void> delete(
            @PathVariable @Parameter(description = "Task id", required = true) Long id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }
}
