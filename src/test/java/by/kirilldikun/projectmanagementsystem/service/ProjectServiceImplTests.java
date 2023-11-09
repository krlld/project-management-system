package by.kirilldikun.projectmanagementsystem.service;

import by.kirilldikun.projectmanagementsystem.dto.ProjectDto;
import by.kirilldikun.projectmanagementsystem.entity.Project;
import by.kirilldikun.projectmanagementsystem.entity.Status;
import by.kirilldikun.projectmanagementsystem.exception.ProjectAlreadyExistsException;
import by.kirilldikun.projectmanagementsystem.exception.ProjectNotFoundException;
import by.kirilldikun.projectmanagementsystem.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTests {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    public void findAllTest() {
        String name = "project";
        Pageable pageable = PageRequest.of(0, 10);
        Page<Project> projects = createProjectPage();
        Page<ProjectDto> projectDtos = createProjectDtoPage();
        Mockito.when(projectRepository.findAllByNameContainsIgnoreCase(name, pageable)).thenReturn(projects);
        Assertions.assertEquals(projectDtos, projectService.findAll(name, pageable));
    }

    @Test
    public void findById_withIncorrectId_throwException() {
        Long id = 1L;
        Mockito.when(projectRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(ProjectNotFoundException.class, () -> projectService.findById(id));
    }

    @Test
    public void findById_withCorrectId_returnProjectDto() {
        Long id = 1L;
        Project project = createProject();
        ProjectDto projectDto = createProjectDto();
        Mockito.when(projectRepository.findById(id)).thenReturn(Optional.of(project));
        Assertions.assertEquals(projectDto, projectService.findById(id));
    }

    @Test
    public void save_withRepeatedId_throwException() {
        ProjectDto projectDto = createProjectDto();
        Mockito.when(projectRepository.existsById(projectDto.getId())).thenReturn(true);
        Assertions.assertThrows(ProjectAlreadyExistsException.class, () -> projectService.save(projectDto));
    }

    @Test
    public void save_withProjectDto_returnSavedProjectDto() {
        ProjectDto projectDto = createProjectDto();
        Project project = createProject();
        Mockito.when(projectRepository.existsById(projectDto.getId())).thenReturn(false);
        Mockito.when(projectRepository.save(project)).thenReturn(project);
        Assertions.assertEquals(projectDto, projectService.save(projectDto));
    }

    @Test
    public void delete_withIncorrectId_throwException() {
        Long id = 1L;
        Mockito.when(projectRepository.existsById(id)).thenReturn(false);
        Assertions.assertThrows(ProjectNotFoundException.class, () -> projectService.delete(id));
    }

    @Test
    public void mapToProjectTest() {
        ProjectDto projectDto = createProjectDto();
        Project project = createProject();
        Project testProject = projectService.mapToProject(projectDto);
        Assertions.assertEquals(project, testProject);
    }

    @Test
    public void mapToProjectDtoTest() {
        Project project = createProject();
        ProjectDto projectDto = createProjectDto();
        ProjectDto testProjectDto = projectService.mapToProjectDto(project);
        Assertions.assertEquals(projectDto, testProjectDto);
    }

    public Project createProject() {
        return Project.builder()
                .id(1L)
                .name("Project Alpha")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                .budget(new BigDecimal("50000.00"))
                .startAt(LocalDate.parse("2023-01-01"))
                .endAt(LocalDate.parse("2023-03-15"))
                .status(Status.IN_PROGRESS)
                .build();
    }

    public ProjectDto createProjectDto() {
        return ProjectDto.builder()
                .id(1L)
                .name("Project Alpha")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                .budget(new BigDecimal("50000.00"))
                .startAt(LocalDate.parse("2023-01-01"))
                .endAt(LocalDate.parse("2023-03-15"))
                .status(Status.IN_PROGRESS)
                .build();
    }

    public Page<Project> createProjectPage() {
        return new PageImpl<>(List.of(createProject(), createProject(), createProject()));
    }

    public Page<ProjectDto> createProjectDtoPage() {
        return new PageImpl<>(List.of(createProjectDto(), createProjectDto(), createProjectDto()));
    }
}
