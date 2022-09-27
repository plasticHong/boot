package com.spring.controller;

import com.spring.domain.Member;
import com.spring.exception.BadRequestException;
import com.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
@SessionAttributes("member")
public class MemberController {

    @Autowired
    MemberService service;

    @GetMapping("login")
    public String login() {

        return "/member/login";
    }

    @PostMapping("login")
    public String login(Member member, Model model) {
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

        return "/member/join";
    }

    @PostMapping("join")
    @ResponseBody
    public Object doJoin(Member member, Model model) {

        System.out.println(member.toString());

        try {

            Member joinMember = service.memberJoin(member);

        }catch (BadRequestException e){
            System.out.println(e.getMessage());

            return new ResponseEntity<Member>(member,HttpStatus.BAD_REQUEST);

        }

        return  new ResponseEntity<Member>(member,HttpStatus.OK);

    }

    @GetMapping("profile")
    public String  profile() {

        return "/member/profile";
    }

    @PostMapping("update")
    public String  update(Member member) {
        service.updateMember(member);

        return "/member/profile";
    }

}
