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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GuestBookController {

    @Autowired
    GuestBookService service;


    @GetMapping("/guestBook")
     public String  getPage(PageRequestDto pageRequestDto, Model model) {

        System.out.println(pageRequestDto.getType());

        PageResultDto<GuestBookDTO, GuestBook> list = service.getList(pageRequestDto);

        model.addAttribute("result",list);


        return "/guestbookList";
    }


    @GetMapping("/guestbook/insertGuestBook")
    public String insertGuestBook() {


        return "/guestbook/insertGuestBook";
    }

    @PostMapping("/insert")
    public String insert(GuestBookDTO dto , RedirectAttributes redirectAttributes) {
        service.insert(dto);

        return "redirect:guestbookList";
    }

}
