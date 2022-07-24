package com.pfe.projectsmanagements.exceptions.Journalist;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JournalistExceptionController {
    @ExceptionHandler
    public ResponseEntity<Object> exception(JournalistNotFoundException exception) {
        return new ResponseEntity<>("Journalist Not Found ", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> exception(JournalistExisteAlready exception) {
        return new ResponseEntity<>("Journalist Already Existe ! please try a gain ", HttpStatus.NOT_FOUND);
    }
}
