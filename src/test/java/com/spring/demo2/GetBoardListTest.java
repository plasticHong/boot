package com.spring.demo2;


import com.spring.domain.Board;
import com.spring.domain.Member;
import com.spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class GetBoardListTest {


    @Autowired
    MemberRepository repository;


    @Test
    public void boardListTEst() {

        Optional<Member> wee = repository.findById("wee");

        List<Board> boardList = wee.get().getBoardList();

        for (Board board:boardList
             ) {
            System.out.println(board.toString());
        }

    }
}
