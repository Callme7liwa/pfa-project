package com.pfe.projectsmanagements.services.Journalist;

import com.pfe.projectsmanagements.Dto.Journalist.JournalistRequestDto;
import com.pfe.projectsmanagements.Dto.Journalist.JournalistResponseDto;
import com.pfe.projectsmanagements.entities.Journalist;

import java.util.List;

public interface JournalistService {

    public JournalistResponseDto saveJournalist(JournalistRequestDto requestDto);

    public Journalist addRoleToUser(Long journalistId ,  String Role);


    public JournalistResponseDto  getJournalist(Long id);

    public Journalist getFullJournalist(Long id );

    public Journalist getJournalistByUsername(String name);

    public List<JournalistResponseDto> getAllJournalistes();

    public List<Journalist> getFullJournalistes();


    public JournalistResponseDto updateJournalist(JournalistRequestDto  journalistRequestDto , Long id );

    public boolean deleteJournalist(Long id );
}
