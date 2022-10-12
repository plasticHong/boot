package com.spring.repository;

import com.spring.domain.Board;
import com.spring.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.JoinColumn;


//@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

//쿼리 어노테이션
//    @Query("select b from board b order by b.seq desc ")
//    List<Board> annotaionTest(Pageable pager);

    //쿼리 메소드 페이징 사용
    //제목 검색어가 포함된 게시글 목록을 조회, 페이징 처리(내림차순)
    //List<Board>findByTitleContaining(String searchKeyWord, Pageable paging);
    @JoinColumn(name = "member_id")
    Page<Board> findByTitleContaining(String searchKeyWord, Pageable paging);


    //회원
    @Query(value ="select b, w from board b left join b.member_id w where b.seq = :seq", nativeQuery = true)
    Object getBoardWithWriter(@Param("seq") Long seq);

//    @Query("select * from board where seq = :seq")
//    Object getBoardWithWriter(@Param("seq") Long seq);


    //게시글


    @Query(value = "select b,w count(r)" +
            "from board b " +
            "left join b.member_id w " +
            "left outer join reply r on r.board_seq = b" +
            "where b.seq = :seq", nativeQuery = true)
    public Object getBoardBySeq(@Param("seq")Long seq);

}
