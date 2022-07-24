package com.pfe.projectsmanagements.controllers.Journalist;

import com.pfe.projectsmanagements.Dto.Journalist.JournalistRequestDto;
import com.pfe.projectsmanagements.Dto.Journalist.JournalistResponseDto;
import com.pfe.projectsmanagements.entities.Journalist;
import com.pfe.projectsmanagements.services.Journalist.JournalistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/journalistes")
public class JournalistRestController {

    private JournalistService journalistService ;



    @Autowired
    public JournalistRestController(JournalistService journalistService )
    {
        this.journalistService = journalistService;
    }

    @RequestMapping(value = "" , method = RequestMethod.POST , consumes = "application/json" , produces = "application/json")
    public ResponseEntity<JournalistResponseDto> saveJournalist(@RequestBody JournalistRequestDto journalistRequestDto)
    {
        JournalistResponseDto journalistResponseDto = journalistService.saveJournalist(journalistRequestDto);
        if(journalistResponseDto != null)
            return ResponseEntity.ok(journalistResponseDto) ;
        throw new RuntimeException("Error while creating a new employee ");
    }

    @RequestMapping(value = "/addRole/{id}" , method = RequestMethod.PUT , consumes = "application/json" , produces = "application/json")
    public ResponseEntity<Journalist> addRoleToJournalist(@PathVariable("id") Long id , @RequestBody String name)
    {
        Journalist journalist = journalistService.addRoleToUser(id,String.valueOf(name));
        return ResponseEntity.ok().body(journalist);
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET , consumes = "application/json" , produces = "application/json")
    public ResponseEntity<?> getJournalist(@PathVariable("id") Long id )
    {
        JournalistResponseDto journalistResponseDto = journalistService.getJournalist(id);
        if(Objects.nonNull(journalistResponseDto))
            return ResponseEntity.status(HttpStatus.FOUND).body(journalistResponseDto);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO USER HERE WITH THIS ID");
    }

    @RequestMapping(value = "/fullJournalist/{id}" , method = RequestMethod.GET , consumes = "application/json" , produces = "application/json")
    public ResponseEntity<?> getFullJournalistes(@PathVariable("id") Long id)
    {
        Journalist  journalist = journalistService.getFullJournalist(id);
        if(Objects.nonNull(journalist))
            return ResponseEntity.status(HttpStatus.FOUND).body(journalist);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO JOURNALIST HAS BEEN FOUND WITH THIS ID"+id);
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.PUT , consumes = "application/json" , produces = "application/json")
    public ResponseEntity<?> updateJournalist(@PathVariable("id") Long id  , @RequestBody JournalistRequestDto requestDto)
    {
        JournalistResponseDto journalistResponseDto = journalistService.updateJournalist(requestDto,id);
        if(Objects.nonNull(journalistResponseDto))
            return ResponseEntity.status(HttpStatus.FOUND).body(journalistResponseDto);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO JOURNALIST HAS BEEN FOUND WITH THIS ID"+id);
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE , consumes = "application/json" , produces = "application/json")
    public ResponseEntity<?> deleteJournalist(@PathVariable("id") Long id)
    {
        boolean booleanResponse = journalistService.deleteJournalist(id);
        if(booleanResponse)
            return ResponseEntity.status(HttpStatus.OK).body("JOURNALIST IS DELETED "+id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO JOURNALIST WITH THIS ID "+id);
    }
}
