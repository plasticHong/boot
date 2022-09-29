package com.spring.demo2;


import com.spring.domain.Board;
import com.spring.domain.Member;
import com.spring.repository.BoardRepository;
import com.spring.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

@SpringBootTest
public class QueryMethodTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BoardRepository boardRepo;

    @Autowired
    private MemberRepository memberRepo;

//    @BeforeEach
//    public void insertDummy() {
//        Member member = new Member();
//        member.setId("wee");
//        member.setName("wee");
//        member.setRole("ROLE_ADMIN");
//        member.setPassword("wee");
//
//        for (int i = 1; i <= 50; i++) {
//            Board board = new Board();
//
//
//
//
//            board.setTitle("wee" + i);
//            board.setContent("content " + i);
//            board.setMember(member);
//            board.setCreateDate(new Date());
//            board.setCnt(0);
//
//            boardRepo.save(board);
//        }
//    }


    @Test
    void testFindTitleContaining(){
        Pageable paging = PageRequest.of(0, 10);

        Page<Board> list = boardRepo.findByTitleContaining("wee",paging);
        log.info("size = "+list.getSize());
        log.info("TotalPages = "+list.getTotalPages());
        log.info("TotalElements = "+list.getTotalElements());
        for (Board board:list
             ) {
            log.info(board.toString());
        }

    }


//    @BeforeEach
//    public void testManyToOneInsert(){
//
//        Member member1 = new Member();
//        member1.setPassword("wee");
//        member1.setName("wee");
//        member1.setRole("ROLE_ADMIN");
//        member1.setId("wee");
//
//        memberRepo.save(member1);
//
//        Member member2 = new Member();
//        member2.setPassword("wee2");
//        member2.setName("wee");
//        member2.setRole("ROLE_ADMIN");
//        member2.setId("wee2");
//
//        memberRepo.save(member2);
//
//        for (int i = 1; i <= 3; i++) {
//
//            Board board = new Board();
//            board.setContent("wee");
//            board.setMember(member1);
//            board.setTitle("wee");
//
//            boardRepo.save(board);
//        }
//
//        for (int i = 1; i <= 3; i++) {
//
//            Board board = new Board();
//            board.setContent("wee");
//            board.setMember(member2);
//            board.setTitle("wee");
//
//            boardRepo.save(board);
//        }
//
//    }



    @Test
    public void testManyToOneSelect(){
        Optional<Board> board = boardRepo.findById(1L);
        board.ifPresent(value -> log.info(value.toString()));
    }


    @AfterEach
    public void clear(){
        boardRepo.deleteAll();
    }
}



