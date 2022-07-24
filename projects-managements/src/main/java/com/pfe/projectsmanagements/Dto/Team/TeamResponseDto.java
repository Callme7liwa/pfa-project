package com.pfe.projectsmanagements.Dto.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponseDto {

    private String name ;
    private String photo ;
    private Integer numberEtoiles ;
    private Date creationDate ;
}
