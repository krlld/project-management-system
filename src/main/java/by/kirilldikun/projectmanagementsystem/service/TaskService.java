package by.kirilldikun.projectmanagementsystem.service;

import by.kirilldikun.projectmanagementsystem.dto.TaskDto;
import by.kirilldikun.projectmanagementsystem.entity.Task;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface TaskService {

    Page<TaskDto> findAll(String name, Pageable pageable);

    TaskDto findById(Long id);

    TaskDto save(@Valid TaskDto taskDto);

    TaskDto update(Long id, @Valid TaskDto taskDto);

    @Transactional
    void delete(Long id);

    Task mapToTask(TaskDto taskDto);

    TaskDto mapToTaskDto(Task task);
}
