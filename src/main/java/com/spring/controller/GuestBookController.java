package com.spring.controller;

import com.spring.dto.GuestBookDTO;
import com.spring.dto.PageRequestDto;
import com.spring.dto.PageResultDto;
import com.spring.entity.GuestBook;
import com.spring.service.GuestBookService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestBookController {

    @Autowired
    GuestBookService service;


    @GetMapping("/guestBook")
     public String  getPage(PageRequestDto pageRequestDto, Model model) {

        PageResultDto<GuestBookDTO, GuestBook> list = service.getList(pageRequestDto);

        model.addAttribute("result",list);


        return "/guestbookList";
    }


}
