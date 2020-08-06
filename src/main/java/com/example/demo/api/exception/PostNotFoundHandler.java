package com.example.demo.api.exception;

import com.example.demo.api.model.PostException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class PostNotFoundHandler {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException exception){

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        PostException postException = new PostException(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.systemDefault())
        );
        return new ResponseEntity<>(postException, httpStatus);

    }

}
