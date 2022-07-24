package com.pfe.projectsmanagements.controllers.Team;

import com.pfe.projectsmanagements.Dto.Team.TeamRequestDto;
import com.pfe.projectsmanagements.Dto.Team.TeamResponseDto;
import com.pfe.projectsmanagements.entities.Team;
import com.pfe.projectsmanagements.services.Team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("")
    public ResponseEntity<?> saveTeam(@RequestBody TeamRequestDto teamRequestDto)
    {
        if(Objects.nonNull(teamService.saveTeam(teamRequestDto)))
            return ResponseEntity.status(HttpStatus.OK).body(teamService.saveTeam(teamRequestDto));
        throw new RuntimeException("Error occure while creating a new team ! ");
    }

    @GetMapping("/{id}")
    public TeamResponseDto getTeam(@PathVariable("id") Long id)
    {
        return teamService.getTeam(id);
    }

    @GetMapping("/full/{id}")
    public Team getFullTeam(@PathVariable("id") Long id)
    {
        return teamService.getFullTeam(id);
    }

    @GetMapping("")
    public ResponseEntity<List<TeamResponseDto>> getAllTeams()
    {
        List<TeamResponseDto> response = teamService.getTeams();
        if(response.size() > 0)
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


}
