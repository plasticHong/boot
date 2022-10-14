package com.spring.service;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.spring.domain.Board;
import com.spring.domain.GuestBook;
import com.spring.domain.QBoard;
import com.spring.domain.QGuestBook;
import com.spring.dto.BoardDTO;
import com.spring.dto.GuestBookDTO;
import com.spring.dto.PageRequestDto;
import com.spring.dto.PageResultDto;
import com.spring.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class BoardServiceImp implements BoardService{


    private BoardRepository boardRepo;

    //게시글 목록 보기
    @Override
    public PageResultDto<BoardDTO,Board> getBoardList(PageRequestDto requestDto) {
        Pageable pageable = PageRequest.of(0,10, Sort.Direction.DESC,"seq");
        BooleanBuilder builder = getSearch(requestDto);

        Page<Board> result = boardRepo.findAll(builder,pageable);

        Function<Board, BoardDTO> fn = (entity -> toDto(entity));
        return new PageResultDto<>(result,fn);
    }


    @Override
    public BooleanBuilder getSearch(PageRequestDto requestDto) {
        String type = requestDto.getType();
        String keyword = requestDto.getKeyword();

        BooleanBuilder builder = new BooleanBuilder();

        QBoard qBoard = QBoard.board;

        BooleanExpression expression = qBoard.seq.gt(0L);
        builder.and(expression);

        //null처리

        if(Objects.isNull(type)||type.trim().length() == 0){
            return builder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t")){
            conditionBuilder.or(qBoard.title.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qBoard.content.contains(keyword));
        }
//        if(type.contains("w")){
//            conditionBuilder.or(qBoard.member.contains(keyword));
//        }

        builder.and(conditionBuilder);

        return builder;
    }

    //게시글 등록
    @Override
    public void insertBoard(Board board) {

        if(Objects.isNull(board)){
            throw new RuntimeException();
        }

        boardRepo.save(board);
    }

    @Override
    public Board getBoard(Long seq) {

        Optional<Board> byId = boardRepo.findById(seq);
        Board board = byId.orElseThrow(() -> new NoSuchElementException());

        updateCnt(board);

        return board;
    }

    @Override
    public void updateCnt(Board board) {
        int cnt = board.getCnt();
        cnt++;
        board.setCnt(cnt);

        boardRepo.save(board);
    }

    //게시글 상세 조회


    //게시글 수정
    @Override
    public void updateBoard(Board board) {
        Optional<Board> byId = boardRepo.findById(board.getSeq());

        byId.orElseThrow(NoSuchElementException::new);

        byId.ifPresent(a->{
            Board board1 = byId.get();
            board1.setTitle(board.getTitle());
            board1.setContent(board.getContent());
            boardRepo.save(board1);
        });


    }

    //게시글 삭제
    @Override
    public void deleteBoard(Board board) {
        boardRepo.delete(board);
    }

}
