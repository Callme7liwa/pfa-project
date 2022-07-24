package com.pfe.projectsmanagements.Dto.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequestDto {

    private String name ;
    private String photo ;
    private List<Long> journalistsId = new ArrayList<Long>();

}
