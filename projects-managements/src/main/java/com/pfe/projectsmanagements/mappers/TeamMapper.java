package com.pfe.projectsmanagements.mappers;

import com.pfe.projectsmanagements.Dto.Team.TeamRequestDto;
import com.pfe.projectsmanagements.Dto.Team.TeamResponseDto;
import com.pfe.projectsmanagements.entities.Team;
import org.springframework.beans.BeanUtils;

public class TeamMapper extends SupperMapper<TeamRequestDto, TeamResponseDto, Team> {

    public static TeamMapper instance = null ;

    public static TeamMapper getInstance() {
        if(instance == null)
            instance = new TeamMapper();
        return  instance ;
    }
    @Override
    public Team DtoToEntity(TeamRequestDto teamRequestDto) {
        Team team  = new Team();
        BeanUtils.copyProperties(teamRequestDto,team);
        return team ;
    }

    @Override
    public TeamResponseDto EntityToDto(Team team) {
        TeamResponseDto teamResponseDto = new TeamResponseDto() ;
        BeanUtils.copyProperties(team,teamResponseDto);
        return teamResponseDto;
    }


}
