package com.pfe.projectsmanagements.services.Project;

import com.pfe.projectsmanagements.Dto.ProjectDto.ProjectRequestDto;
import com.pfe.projectsmanagements.Dto.ProjectDto.ProjectResponseDto;
import com.pfe.projectsmanagements.dao.ProjectRepository;
import com.pfe.projectsmanagements.entities.Project;
import com.pfe.projectsmanagements.entities.Tach;
import com.pfe.projectsmanagements.entities.Team;
import com.pfe.projectsmanagements.mappers.ProjectMapper;
import com.pfe.projectsmanagements.services.Tache.TachService;
import com.pfe.projectsmanagements.services.Team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectServiceImpl  implements  ProjectService{

    private Random random ;

    private ProjectMapper projectMapper ;

    private ProjectRepository projectRepository;

    @Autowired
    private TachService tachService ;

    @Autowired
    private TeamService teamService ;



    public ProjectServiceImpl( ProjectRepository projectRepository , Random random  ){
        this.projectMapper = ProjectMapper.getInstance();
        this.projectRepository = projectRepository;
        this.random = random ;
    }


    @Override
    public ProjectResponseDto saveProject(ProjectRequestDto request) {
        Project  project = projectMapper.DtoToEntity(request);
        if(request.getTachesNames().size()>0)
        {
            Set<Tach> taches =
                    request.getTachesNames()
                            .stream()
                            .map(name->{
                                return tachService.getTach(name);
                            }).collect(Collectors.toSet());
            project.setTaches(taches);
        }

        Team team = teamService.getFullTeam(request.getTeamId());
        project.setCreationDate(new Date());
        project.setId(random.nextLong());
        project.setTeam(team);
        project = projectRepository.save(project);
        return projectMapper.EntityToDto(project);
    }

    @Override
    public ProjectResponseDto getProject(Long id) {
        return null ;
    }

    @Override
    public Project getFullProject(Long id) {
        Optional <Project> projectOptional = projectRepository.findById(id);
        if(projectOptional.isPresent())
            return projectOptional.get();
        return null ;
    }

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> getFullProjects() {
        return null;
    }

    @Override
    public boolean deleteProject(Long id) {
        return false;
    }
}
