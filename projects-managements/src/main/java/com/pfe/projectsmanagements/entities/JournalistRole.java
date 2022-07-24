package com.pfe.projectsmanagements.entities;

import com.pfe.projectsmanagements.Enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalistRole {

    private Long id ;
    private ERole role ;
}
