package com.pfe.projectsmanagements.Dto.Journalist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalistResponseDto {
    private String firstName ;
    private String secondName ;
    private String userName ;
    private String email ;
    private String gender ;
    private String country ;
    private String city ;
    private Date birthday ;
}
