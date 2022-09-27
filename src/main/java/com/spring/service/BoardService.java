package com.spring.service;

import com.spring.domain.Board;

import java.util.ArrayList;
import java.util.List;

public interface BoardService {
    List<Board> getBoardList();

    void insertBoard(Board board);

    Board getBoard(Long seq);

    void updateBoard(Board board);

    void deleteBoard(Board board);
}
