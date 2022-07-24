package com.pfe.projectsmanagements.exceptions.Team;

import com.pfe.projectsmanagements.exceptions.Journalist.JournalistNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TeamExceptionController {
    @ExceptionHandler
    public ResponseEntity<Object> exception(TeamNotFoundException exception) {
        return new ResponseEntity<>("Team Not Found ", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<Object> exception(TeamAlreadyExisteException exception) {
        return new ResponseEntity<>("Team Already Exist with this name ", HttpStatus.BAD_REQUEST);
    }
}
