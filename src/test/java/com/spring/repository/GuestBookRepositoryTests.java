package com.spring.repository;

import com.spring.domain.Board;
import com.spring.domain.Member;
import com.spring.domain.Reply;
import com.spring.domain.Role;
import com.spring.entity.GuestBook;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.beans.Transient;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepositoryTests {

    Logger log = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    GuestBookRepository repo;

    @Autowired
    MemberRepository repo;

    @Autowired
    ReplyRepository rrepo;

//    @Test
//    public void insertDate() {
//        IntStream.rangeClosed(1,300).forEach(i ->{
//
//            GuestBook guestBook = GuestBook.builder()
//                    .title("title"+i)
//                    .content("content"+i)
//                    .writer("user"+(i%10))
//                    .build();
//            log.info("save : "+repo.save(guestBook));
//
//        });
//    }


//    @Test
//    public void updateTest() {
//        Optional<GuestBook> result = repo.findById(2222L);
//
//        result.ifPresent(a->{
//
//            GuestBook guestBook = result.get();
//
//            guestBook.setContent("수정");
//
//            repo.save(guestBook);
//        });
//    }


//    @Test
//    public void insertTest() {
//        IntStream.rangeClosed(1,100).forEach(i -> {
//            Member member = Member.builder()
//                    .id("wee"+i)
//                    .name("wee"+i)
//                    .password("wee"+i)
//                    .role(Role.ROLE_USER)
//                    .build();
//            repo.save(member);
//        });
//    }

//   s

}
