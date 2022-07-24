package com.pfe.projectsmanagements.services.Project;

import com.pfe.projectsmanagements.Dto.ProjectDto.ProjectRequestDto;
import com.pfe.projectsmanagements.Dto.ProjectDto.ProjectResponseDto;
import com.pfe.projectsmanagements.entities.Project;

import java.util.List;

public interface ProjectService {

    public ProjectResponseDto saveProject(ProjectRequestDto request);

    public ProjectResponseDto getProject(Long id);

    public Project getFullProject(Long id );

    public List<Project> getProjects();

    public List<Project> getFullProjects();

    public boolean deleteProject(Long id);

}

