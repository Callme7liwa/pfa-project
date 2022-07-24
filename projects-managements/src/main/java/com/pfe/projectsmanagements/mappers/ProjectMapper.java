package com.pfe.projectsmanagements.mappers;

import com.pfe.projectsmanagements.Dto.ProjectDto.ProjectRequestDto;
import com.pfe.projectsmanagements.Dto.ProjectDto.ProjectResponseDto;
import com.pfe.projectsmanagements.entities.Project;
import org.springframework.beans.BeanUtils;

public class ProjectMapper extends SupperMapper<ProjectRequestDto, ProjectResponseDto, Project> {

    public static ProjectMapper projectMapper =null ;

    public static ProjectMapper getInstance()
    {
        if(projectMapper==null)
                  projectMapper = new ProjectMapper();
        return projectMapper ;
    }

    @Override
    public Project DtoToEntity(ProjectRequestDto projectRequestDto) {
        Project project = new Project();
        BeanUtils.copyProperties(projectRequestDto , project);
        return project ;
    }

    @Override
    public ProjectResponseDto EntityToDto(Project project) {
        ProjectResponseDto projectResponseDto = new ProjectResponseDto();
        BeanUtils.copyProperties(project,projectResponseDto);
        return projectResponseDto;
    }
}
