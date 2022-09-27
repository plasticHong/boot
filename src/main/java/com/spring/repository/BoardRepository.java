package com.spring.repository;

import com.spring.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.JoinColumn;


@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

//쿼리 어노테이션
//    @Query("select b from board b order by b.seq desc ")
//    List<Board> annotaionTest(Pageable pager);

    //쿼리 메소드 페이징 사용
    //제목 검색어가 포함된 게시글 목록을 조회, 페이징 처리(내림차순)
    //List<Board>findByTitleContaining(String searchKeyWord, Pageable paging);
    @JoinColumn(name = "member_id")
    Page<Board> findByTitleContaining(String searchKeyWord, Pageable paging);

}
