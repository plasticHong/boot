package com.spring.controller;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.spring.domain.Member;
import com.spring.dto.BoardDTO;
import com.spring.dto.FileDTO;
import com.spring.dto.PageRequestDto;
import com.spring.dto.PageResultDto;
import com.spring.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.spring.domain.Board;
import com.spring.domain.Member;
import com.spring.service.BoardService;
import org.springframework.web.multipart.MultipartFile;

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
    public String getBoardList(Model model, @ModelAttribute("member") Member member, PageRequestDto pageRequestDto) {

        PageResultDto<BoardDTO,Board> boardList = service.getBoardList(pageRequestDto);

        model.addAttribute("boardList", boardList);


        return "board/boardList";
    }

    //게시글 등록 폼 요청
    @GetMapping("/insertBoard")
    public String insertBoard(@ModelAttribute("member") Member member) {

        return "board/insertBoard";
    }



    //게시글 등록 처리
    @PostMapping("/insertBoard")
    public String insertBoard(
            Board board,
            @AuthenticationPrincipal SecurityUser principal,
            @RequestParam MultipartFile[] uploadFile) throws IOException {
        //@AuthenticationPrincipal 애노테이션을 사용하면 UserDetailsService에서 Return한 객체 를 파라메터로 직접 받아 사용

        for (MultipartFile file : uploadFile) {
            if (!file.isEmpty()) {
                FileDTO dto = new FileDTO(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());

                File newFileName = new File(dto.getUid() + "_" + dto.getFileName());
                file.transferTo(newFileName);
            }


        }


        board.setMember(principal.getMember());
        service.insertBoard(board);
        return "redirect:getBoardList";
    }

    //게시글 상세 조회
    @GetMapping("/getBoard")
    public String getBoard(Long seq, Model model) {
        Board board = service.getBoard(seq);
        model.addAttribute(board);
        return "board/getBoard";
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



