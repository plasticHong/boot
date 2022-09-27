package com.spring.demo2;

import com.spring.domain.Board;
import com.spring.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class BoardRepositoryTest {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BoardRepository boardRepo;


//    public BoardRepositoryTest(BoardRepository boardRepo) {
//        this.boardRepo = boardRepo;
//    }

//    @Test
//    public void saveTest(){
//        Board board  = new Board();
//
//        board.setCnt(1);
//        board.setTitle("Title");
//        board.setSeq(1);
//        board.setContent("content");
//        board.setWriter("wee");
//        board.setCreateDate(new Date());
//
//        boardRepo.save(board);
//
//    }
//
//
//    @Test
//    public void getBoardTest(){
//
//        Optional<Board> board = boardRepo.findById(1);
//
//        log.info("board",board);
//
//    }
//
//    @Test
//    public void getAllTest(){
//
//        Iterable<Board> list = boardRepo.findAll();
//
//        for (Board board:list
//             ) {
//            log.info(board.toString());
//        }
//    }
//
//    @Test
//    public void updateTest(){
//       Board board = boardRepo.findById(1).get();
//
//        log.info(board.getTitle());
//
//        board.setTitle("수정된 제목");
//        boardRepo.save(board);
//        Board board2 = boardRepo.findById(1).get();
//        System.out.println(board2.getTitle());
//
//    }



}
