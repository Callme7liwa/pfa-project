package com.pfe.projectsmanagements.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("teams")
@ToString
public class Team {

    private Long id ;
    @NotNull(message = "name of the team should not be null  ! ")
    @Indexed(unique = true)
    private String name ;
    private String photo ;
    @NotNull(message = "creation date of the team  should not be null !")
    private Date creationDate ;
    private Integer numberEtoiles ;
    @DBRef
    private Set<Journalist> journalists = new HashSet<>();
    @DBRef
    private Set<Project> projects  = new HashSet<>();
}
