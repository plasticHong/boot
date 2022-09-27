package com.spring.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Already exists")
public class BadRequestException extends RuntimeException{
    String message = "이미 존재하는 회원입니다";
    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
