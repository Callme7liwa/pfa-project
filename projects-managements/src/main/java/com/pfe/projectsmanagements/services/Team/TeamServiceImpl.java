package com.pfe.projectsmanagements.services.Team;

import com.pfe.projectsmanagements.Dto.Team.TeamRequestDto;
import com.pfe.projectsmanagements.Dto.Team.TeamResponseDto;
import com.pfe.projectsmanagements.dao.TeamRepository;
import com.pfe.projectsmanagements.entities.Journalist;
import com.pfe.projectsmanagements.entities.Project;
import com.pfe.projectsmanagements.entities.Team;
import com.pfe.projectsmanagements.exceptions.Team.TeamAlreadyExisteException;
import com.pfe.projectsmanagements.exceptions.Team.TeamNotFoundException;
import com.pfe.projectsmanagements.mappers.TeamMapper;
import com.pfe.projectsmanagements.services.Journalist.JournalistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private TeamMapper teamMapper ;

    private TeamRepository teamRepository;

    private JournalistService journalistService  ;

    private  Random random ;

    public TeamServiceImpl(Random random, TeamRepository teamRepository , JournalistService journalistService) {
        this.teamMapper = TeamMapper.getInstance();
        this.teamRepository = teamRepository;
        this.journalistService = journalistService ;
        this.random = new Random();
    }


    @Override
    public TeamResponseDto saveTeam(TeamRequestDto teamRequestDto) {
        Team teamSearch = teamRepository.findByName(teamRequestDto.getName());

        if(Objects.isNull(teamSearch)) {

            Team team = teamMapper.DtoToEntity(teamRequestDto);
            team.setId(random.nextLong());
            team.setCreationDate(new Date());
            team.setNumberEtoiles(0);

            if (teamRequestDto.getJournalistsId().size() > 0) {
                Set<Journalist> journalists = teamRequestDto
                        .getJournalistsId()
                        .stream()
                        .map(id -> {
                            Journalist journalist = journalistService.getFullJournalist(id);
                            return journalist;
                        }).collect(Collectors.toSet());
                team.setJournalists(journalists);
            }
            team = teamRepository.save(team);

            if (Objects.nonNull(team))
                return teamMapper.EntityToDto(team);
            return null;
        }

        throw new TeamAlreadyExisteException();
    }

    @Override
    public TeamResponseDto getTeam(Long id) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        if(teamOptional.isPresent())
            return teamMapper.EntityToDto(teamOptional.get());
        throw new TeamNotFoundException();
    }

    @Override
    public Team getFullTeam(Long id) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        if(teamOptional.isPresent())
            return teamOptional.get();
        throw  new TeamNotFoundException();
    }

    @Override
    public List<Team> getFullTeams() {
        Optional<List<Team>> teams = Optional.of(teamRepository.findAll());
        if(teams.isPresent())
            return teams.get();
        throw  null ;
    }

    @Override
    public List<TeamResponseDto> getTeams() {
        Optional<List<Team>> teams = Optional.of(teamRepository.findAll());
        if(teams.isPresent())
            return
                teams
                        .get()
                        .stream()
                        .map(team ->  teamMapper.EntityToDto(team))
                        .collect(Collectors.toList());
        return null ;
    }

    @Override
    public Boolean affecterProject(Long teamId, Project project) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if(teamOptional.isPresent()) {
            Team team = teamOptional.get();
            team.getProjects().add(project);
            teamRepository.save(team);
            return Boolean.TRUE;
        }
        throw new TeamNotFoundException();
    }

    @Override
    public Boolean removeProject(Long teamId, Project project) {
        return null;
    }
}
