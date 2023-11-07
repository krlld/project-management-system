package by.kirilldikun.projectmanagementsystem.service;

import by.kirilldikun.projectmanagementsystem.dto.ProjectDto;
import by.kirilldikun.projectmanagementsystem.entity.Project;
import by.kirilldikun.projectmanagementsystem.exception.ProjectAlreadyExistsException;
import by.kirilldikun.projectmanagementsystem.exception.ProjectNotFoundException;
import by.kirilldikun.projectmanagementsystem.repository.ProjectRepository;
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
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Page<ProjectDto> findAll(String name, Pageable pageable) {
        Page<Project> projects = projectRepository.findAllByNameContainsIgnoreCase(name, pageable);
        return projects.map(this::mapToProjectDto);
    }

    public ProjectDto findById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
        return mapToProjectDto(project);
    }

    public ProjectDto save(@Valid ProjectDto projectDto) {
        if (projectDto.getId() != null && projectRepository.existsById(projectDto.getId())) {
            throw new ProjectAlreadyExistsException();
        }
        Project project = mapToProject(projectDto);
        project = projectRepository.save(project);
        return mapToProjectDto(project);
    }

    public ProjectDto update(Long id, @Valid ProjectDto projectDto) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException();
        }
        Project project = mapToProject(projectDto);
        project.setId(id);
        project = projectRepository.save(project);
        return mapToProjectDto(project);
    }

    @Transactional
    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException();
        }
        projectRepository.softDeleteById(id);
    }

    public boolean existsById(Long id) {
        return projectRepository.existsById(id);
    }

    public Project mapToProject(ProjectDto projectDto) {
        return Project.builder()
                .id(projectDto.getId())
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .budget(projectDto.getBudget())
                .startAt(projectDto.getStartAt())
                .endAt(projectDto.getEndAt())
                .status(projectDto.getStatus())
                .build();
    }

    public ProjectDto mapToProjectDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .budget(project.getBudget())
                .startAt(project.getStartAt())
                .endAt(project.getEndAt())
                .status(project.getStatus())
                .build();
    }
}
