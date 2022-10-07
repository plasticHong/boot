package com.spring.repository;

import com.spring.entity.GuestBook;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepositoryTests {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GuestBookRepository repo;

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


    @Test
    public void updateTest() {
        Optional<GuestBook> result = repo.findById(2222L);

        result.ifPresent(a->{

            GuestBook guestBook = result.get();

            guestBook.setContent("수정");

            repo.save(guestBook);
        });
    }

}
