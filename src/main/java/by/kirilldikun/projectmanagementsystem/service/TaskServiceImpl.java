package by.kirilldikun.projectmanagementsystem.service;

import by.kirilldikun.projectmanagementsystem.dto.TaskDto;
import by.kirilldikun.projectmanagementsystem.entity.Task;
import by.kirilldikun.projectmanagementsystem.exception.ProjectNotFoundException;
import by.kirilldikun.projectmanagementsystem.exception.TaskAlreadyExistsException;
import by.kirilldikun.projectmanagementsystem.exception.TaskNotFoundException;
import by.kirilldikun.projectmanagementsystem.repository.TaskRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final ProjectService projectService;

    @Override
    public Page<TaskDto> findAll(String name, Pageable pageable) {
        Page<Task> tasks = taskRepository.findAllByNameContainsIgnoreCase(name, pageable);
        return tasks.map(this::mapToTaskDto);
    }

    @Override
    public TaskDto findById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        return mapToTaskDto(task);
    }

    @Override
    public TaskDto save(@Valid TaskDto taskDto) {
        if (taskDto.getId() != null && taskRepository.existsById(taskDto.getId())) {
            throw new TaskAlreadyExistsException();
        }
        if (!projectService.existsById(taskDto.getProjectId())) {
            throw new ProjectNotFoundException();
        }
        Task task = mapToTask(taskDto);
        task = taskRepository.save(task);
        return mapToTaskDto(task);
    }

    @Override
    public TaskDto update(Long id, @Valid TaskDto taskDto) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException();
        }
        if (!projectService.existsById(taskDto.getProjectId())) {
            throw new ProjectNotFoundException();
        }
        Task task = mapToTask(taskDto);
        task.setId(id);
        task = taskRepository.save(task);
        return mapToTaskDto(task);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException();
        }
        taskRepository.softDeleteById(id);
    }

    @Override
    public Task mapToTask(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.getId())
                .name(taskDto.getName())
                .description(taskDto.getDescription())
                .startAt(taskDto.getStartAt())
                .endAt(taskDto.getEndAt())
                .status(taskDto.getStatus())
                .project(projectService.mapToProject(projectService.findById(taskDto.getProjectId())))
                .build();
    }

    @Override
    public TaskDto mapToTaskDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .startAt(task.getStartAt())
                .endAt(task.getEndAt())
                .status(task.getStatus())
                .projectId(task.getProject().getId())
                .build();
    }
}
