package com.spring.service;

import com.spring.domain.Board;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface BoardService {
    Page<Board> getBoardList();

    void insertBoard(Board board);

    Board getBoard(Long seq);

    void updateBoard(Board board);

    void deleteBoard(Board board);

    void updateCnt(Board board);
}
