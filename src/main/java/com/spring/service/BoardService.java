package com.spring.service;

import com.querydsl.core.BooleanBuilder;
import com.spring.domain.Board;
import com.spring.domain.GuestBook;
import com.spring.dto.BoardDTO;
import com.spring.dto.GuestBookDTO;
import com.spring.dto.PageRequestDto;
import com.spring.dto.PageResultDto;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface BoardService {
    PageResultDto<BoardDTO,Board> getBoardList(PageRequestDto requestDto);

    public BooleanBuilder getSearch(PageRequestDto requestDto);
    void insertBoard(Board board);

    Board getBoard(Long seq);

    void updateBoard(Board board);

    void deleteBoard(Board board);

    void updateCnt(Board board);


    default public Board toEntity(BoardDTO dto) {
        return
                Board.builder()
                        //          .gno(dto.getGno())
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .member(dto.getMember())
                        .build();
    }

    default public BoardDTO toDto(Board entity) {

        return BoardDTO.builder()
                .seq(entity.getSeq())
                .title(entity.getTitle())
                .content(entity.getContent())
                .member(entity.getMember())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

    }


}
