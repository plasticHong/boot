package com.spring.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
public class BoardRepositoryTest {


    @Autowired
    BoardRepository repo;


    @Test
    public void testBoardWithWriter() {

//        Object boardWithWriter =
                repo.getBoardBySeq(1L);
        System.out.println(Objects.isNull(repo.getBoardBySeq(1L)));
    }



}
