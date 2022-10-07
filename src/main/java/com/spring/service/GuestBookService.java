package com.spring.service;


import com.spring.dto.GuestBookDTO;
import com.spring.dto.PageRequestDto;
import com.spring.dto.PageResultDto;
import com.spring.entity.GuestBook;

import javax.swing.text.html.parser.Entity;

public interface GuestBookService {

    public Long insert(GuestBookDTO guestBook);


    default public GuestBook dtoToEntity(GuestBookDTO dto) {
        return
                GuestBook.builder()
              //          .gno(dto.getGno())
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .writer(dto.getWriter())
                        .build();
    }

    default public GuestBookDTO entityToDto(GuestBook entity) {

        return GuestBookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

    }

    public PageResultDto<GuestBookDTO,GuestBook> getList(PageRequestDto requestDto);
}
