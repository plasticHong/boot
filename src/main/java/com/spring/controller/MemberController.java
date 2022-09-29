package com.spring.controller;

import com.spring.domain.Member;
import com.spring.domain.Role;
import com.spring.exception.BadRequestException;
import com.spring.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member/*")
@SessionAttributes("member")
public class MemberController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MemberService service;

    @GetMapping("login")
    public String login() {

        return "/member/login";
    }

    @PostMapping("login")
    public String login(Member member, Model model,HttpSession session) {
        Member findMember = service.getMember(member);

        if (findMember != null
                && findMember.getPassword().equals(member.getPassword())) {
            model.addAttribute("member", findMember);  //세션 발급
            return "redirect:/getBoardList";
        } else {
            return "redirect:login";
        }


    }

    @GetMapping("logout")
    public String logout(HttpSession session, SessionStatus status) {

        session.invalidate();
        status.setComplete();

        return "redirect:/";
    }

    @GetMapping("join")
    public String joinForm() {
        System.out.println("member.toString()");

        return "/member/join";

    }

    @PostMapping("join")
    public Object doJoin(Member member, Model model) {

            System.out.println("member = "+member.toString());
            Member joinMember = service.memberJoin(member);

        return "/member/login";
    }


    @PostMapping("/check")
    @ResponseBody
    public Object idDuplicateCheck(Member member) {

        System.out.println("zz");

        boolean isDuplicate = service.checkId(member);

        if (isDuplicate) {
            String msg = "이미 존재하는 회원입니다.";
            return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        return "success";
    }


    @GetMapping("profile")
    public String profile() {

        return "/member/profile";
    }

    @PostMapping("update")
    public String update(Member member) {
        service.updateMember(member);

        return "/member/profile";
    }

}
