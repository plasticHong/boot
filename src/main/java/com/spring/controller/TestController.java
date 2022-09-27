package com.spring.controller;

import com.spring.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {


    @Autowired
    private BoardService service;


    private Logger log = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/error/error2")
    public void illegalException() {
        throw new IllegalArgumentException();
    }



    @GetMapping("/getboard")
    public String getBoard(@RequestParam("pk")Long pk, Model model){


        log.info("info level! {}", model.addAttribute("board",service.getBoard(pk).getTitle()));


        return "hello";

    }


    @GetMapping("/getList")
    public String getList(Model model){



        model.addAttribute("list",service.getBoardList());


        return "hello";
    }





}
