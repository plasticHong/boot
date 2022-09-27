package com.spring.service;


import com.spring.domain.Board;
import com.spring.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardServiceImp implements BoardService{


    private BoardRepository boardRepo;

    //게시글 목록 보기
    @Override
    public List<Board> getBoardList() {
        return boardRepo.findAll();
    }

    //게시글 등록
    @Override
    public void insertBoard(Board board) {
        boardRepo.save(board);
    }

    @Override
    public Board getBoard(Long seq) {
        return boardRepo.findById(seq).get();
    }

    //게시글 상세 조회


    //게시글 수정
    @Override
    public void updateBoard(Board board) {
        Board findBoard = boardRepo.findById(board.getSeq()).get();
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        boardRepo.save(findBoard);
    }

    //게시글 삭제
    @Override
    public void deleteBoard(Board board) {
        boardRepo.delete(board);
    }

}
