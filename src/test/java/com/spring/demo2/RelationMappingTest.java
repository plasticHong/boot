package com.spring.demo2;

import com.spring.domain.Board;
import com.spring.domain.Member;
import com.spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RelationMappingTest {


    @Autowired
    MemberRepository memberRepo;


    @Test
    public void testTwoWayMapping(){
        Member member = memberRepo.findById("wee").get();

        List<Board> list = member.getBoardList();

        System.out.println("list = "+list);
    }

}
