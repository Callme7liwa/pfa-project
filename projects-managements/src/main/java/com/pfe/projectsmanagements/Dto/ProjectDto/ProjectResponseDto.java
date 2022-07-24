package com.pfe.projectsmanagements.Dto.ProjectDto;

import com.pfe.projectsmanagements.entities.Tach;
import com.pfe.projectsmanagements.entities.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDto {

    private String name ;
    private String resume ;
    private Team team ;
    private List<Tach> tachesNames ;
}
