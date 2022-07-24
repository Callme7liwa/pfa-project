package com.pfe.projectsmanagements.exceptions.Project;


import com.pfe.projectsmanagements.exceptions.Team.TeamNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TachExceptionController {
    @ExceptionHandler
    public ResponseEntity<Object> exception(TachExisteException exception) {
        return new ResponseEntity<>("Sorry But this Tach already Exist ", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> exception(TachUnfoundException exception) {
        return new ResponseEntity<>("Unfound Tach  !! ", HttpStatus.NOT_FOUND);
    }

}
