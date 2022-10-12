package com.spring.service;

import com.spring.dto.GuestBookDTO;
import com.spring.dto.PageRequestDto;
import com.spring.dto.PageResultDto;
import com.spring.entity.GuestBook;
import com.spring.repository.GuestBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private GuestBookService service;

    @Autowired
    private GuestBookRepository repo;

//    @Test
//    public void insertTest() {
//        GuestBookDTO dto = GuestBookDTO.builder()
//                .title("sample title2")
//                .content("sample content2")
//                .writer("user1")
//                .build();
//
//        Long insertId = service.insert(dto);
//
//        System.out.println(insertId);
//    }


//    @Test
//    public void PagingTest() {
//
//        PageRequestDto pageRequestDto = PageRequestDto.builder()
//                .page(10)
//                .size(10)
//                .build();
//
//        PageResultDto<GuestBookDTO,GuestBook> resultDto =
//                service.getList(pageRequestDto);
//
//
//        for (GuestBookDTO dto : resultDto.getDtoList()
//             ) {
//            System.out.println(dto);
//        }
//
//    }


    @Test
    public void searchTest() {

        PageRequestDto pageRequestDto = PageRequestDto.builder()
                .page(1)
                .size(10)
                .type("t")
                .keyword("한글")
                .build();

        PageResultDto<GuestBookDTO,GuestBook> resultDto = service.getList(pageRequestDto);
        System.out.println("prev : "+resultDto.isPrev());
        System.out.println("next : "+resultDto.isNext());

        for (GuestBookDTO guestBook: resultDto.getDtoList()
             ) {
            System.out.println("검색 결과 : "+guestBook.toString());

        }

    }






}
