package com.spring.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.spring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.spring.domain.Board;
import com.spring.domain.Member;
import com.spring.service.BoardService;

@Controller
@SessionAttributes("member")
public class BoardController {

    @Autowired
    private BoardService service;


    //인증 상태 유지하기
    @ModelAttribute("member")
    public Member setMember() {
        return new Member();
    }


    //게시글 목록
    @GetMapping("/getBoardList")
    public String getBoardList(Model model,@ModelAttribute("member")Member member) {

        List<Board> boardList = (List<Board>) service.getBoardList();

        model.addAttribute("boardList", boardList);

        if(member.getId() == null) {
            return "redirect:/member/login";
        }

        return "/board/boardList";
    }

    //게시글 등록 폼 요청
    @GetMapping("/insertBoard")
    public String insertBoard(@ModelAttribute("member") Member member) {
        //로그인 하지 않은 경우 - 로그인 페이지로 이동
        if(member.getId() == null) {
            return "redirect:/member/login";
        }
        return "/board/insertBoard";
    }

    //게시글 등록 처리
    @PostMapping("/insertBoard")
    public String insertBoard(Board board) {

        service.insertBoard(board);
        return "redirect:getBoardList";
    }

    //게시글 상세 조회
    @GetMapping("/getBoard")
    public String getBoard(Long seq, Model model) {
        Board board = service.getBoard(seq);
        model.addAttribute(board);
        return "/board/getBoard";
    }

    //게시글 삭제
    @GetMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        service.deleteBoard(board);
        return "redirect:getBoardList";
    }

    //게시글 수정
    @PostMapping("/updateBoard")
    public String updateBoard(Board board) {
        service.updateBoard(board);
        return "redirect:getBoardList";
    }




}



