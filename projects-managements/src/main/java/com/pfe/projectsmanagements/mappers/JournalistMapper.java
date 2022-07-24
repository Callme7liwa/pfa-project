package com.pfe.projectsmanagements.mappers;

import com.pfe.projectsmanagements.Dto.Journalist.JournalistRequestDto;
import com.pfe.projectsmanagements.Dto.Journalist.JournalistResponseDto;
import com.pfe.projectsmanagements.Enums.Gender;
import com.pfe.projectsmanagements.entities.Journalist;
import org.springframework.beans.BeanUtils;

public class JournalistMapper extends  SupperMapper<JournalistRequestDto, JournalistResponseDto, Journalist> {


    private static JournalistMapper instance = null;

    public  static JournalistMapper getInstance () {
        if(instance == null )
            instance = new JournalistMapper();
        return  instance ;
    }

    @Override
    public Journalist DtoToEntity(JournalistRequestDto journalistRequestDto) {
        Journalist journalist = new Journalist();
        BeanUtils.copyProperties(journalistRequestDto,journalist);
        if(journalistRequestDto.getGender().toLowerCase().equals("male"))
            journalist.setGender(Gender.MALE);
        else
            journalist.setGender(Gender.FEMALE);
        return  journalist ;
    }

    @Override
    public JournalistResponseDto EntityToDto(Journalist journalist) {
        JournalistResponseDto responseDto = new JournalistResponseDto() ;
        BeanUtils.copyProperties(journalist,responseDto);
        return  responseDto;
    }
}
