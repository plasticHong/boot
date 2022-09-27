package com.spring.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Already exists")
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }

}
