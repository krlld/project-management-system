package by.kirilldikun.projectmanagementsystem.service;

import by.kirilldikun.projectmanagementsystem.dto.ProjectDto;
import by.kirilldikun.projectmanagementsystem.entity.Project;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface ProjectService {

    Page<ProjectDto> findAll(String name, Pageable pageable);

    ProjectDto findById(Long id);

    ProjectDto save(@Valid ProjectDto projectDto);

    ProjectDto update(Long id, @Valid ProjectDto projectDto);

    @Transactional
    void delete(Long id);

    boolean existsById(Long id);

    Project mapToProject(ProjectDto projectDto);

    ProjectDto mapToProjectDto(Project project);
}
