package com.pfe.projectsmanagements.entities;

import com.pfe.projectsmanagements.Enums.ProjectStatus;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Document("projects")
public class Project {
    private Long id ;
    private String projectName ;
    private String resume ;
    private ProjectStatus status ;
    private Date creationDate ;
    @DBRef
    private Team team ;
    @DBRef
    private Set<Tach> taches = new HashSet<>( ) ;
}
