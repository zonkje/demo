package com.example.demo.api.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class PostException {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

}
