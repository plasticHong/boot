package com.spring.controller;


import ch.qos.logback.classic.Logger;
import com.spring.exception.BadRequestException;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.http.HttpRequest;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionController {




    Logger log = (Logger) LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(NoHandlerFoundException.class)
    public String notFound(NoHandlerFoundException exception, Model model){

        model.addAttribute("error",exception.getMessage());

        return "/error/404";
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public String Illegal(IllegalArgumentException exception, Model model) {

        String msg = ""+exception.getMessage()+", "+exception.getStackTrace();
        model.addAttribute("error",msg);
        return "/error/illegal";

    }

    @ExceptionHandler(NoSuchElementException.class)
    public String element(NoSuchElementException exception,Model model) {
        model.addAttribute("error",exception.getMessage());
        return "/error/error2";
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public String alreadyExist(){
        
        return "<script>alert('이미 존재하는 회원입니다');location.href='/member/join'</script>";
    }


}
