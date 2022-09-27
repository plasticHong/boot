package com.spring.controller;

import com.spring.domain.Member;
import com.spring.exception.BadRequestException;
import com.spring.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Map;

@Controller
@RequestMapping("/member/*")
@SessionAttributes("member")
public class MemberController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MemberService service;

    @GetMapping("login")
    public String login(HttpServletRequest request) {
        log.info("method : " + request.getMethod() + ", url : " + request.getRequestURL());

        return "/member/login";
    }

    @PostMapping("login")
    public String login(Member member, Model model,HttpServletRequest request) {
        log.info("method : " + request.getMethod() + ", url : " + request.getRequestURL());

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
    public String joinForm(HttpServletRequest request) {
        log.info("method : " + request.getMethod() + ", url : " + request.getRequestURL());
        return "/member/join";

    }

    @PostMapping("join")
    @ResponseBody
    public Object doJoin(Member member, Model model, HttpServletRequest request) {
        log.info("method : " + request.getMethod() + ", url : " + request.getRequestURL());
        try {

            Member joinMember = service.memberJoin(member);

        } catch (BadRequestException e) {
            String msg = "이미 존재하는 회원입니다.";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        HashMap<String, String > map = new HashMap<>();
        map.put("member", "member");
        return new ResponseEntity<>(member,HttpStatus.OK);

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
