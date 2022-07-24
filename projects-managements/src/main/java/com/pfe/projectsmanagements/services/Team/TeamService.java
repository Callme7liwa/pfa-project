package com.pfe.projectsmanagements.services.Team;

import com.pfe.projectsmanagements.Dto.Team.TeamRequestDto;
import com.pfe.projectsmanagements.Dto.Team.TeamResponseDto;
import com.pfe.projectsmanagements.entities.Project;
import com.pfe.projectsmanagements.entities.Team;

import java.util.List;

public interface TeamService {

    public TeamResponseDto saveTeam(TeamRequestDto teamRequestDto);

    public TeamResponseDto getTeam(Long id);

    public Team getFullTeam(Long id );

    public List<Team> getFullTeams();

    public List<TeamResponseDto> getTeams();

    public Boolean affecterProject(Long teamId, Project project);

    public Boolean removeProject(Long teamId , Project project);


}
