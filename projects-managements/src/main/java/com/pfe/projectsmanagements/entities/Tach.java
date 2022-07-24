package com.pfe.projectsmanagements.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Document("taches")
public class Tach {
    @Id
    private Long id ;
    private String name ;
}
