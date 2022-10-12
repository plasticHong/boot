package com.spring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Entity
@DynamicInsert
//@Builder
public class Board implements Serializable {

    private static final long serialVersionUID = 2L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seq")
    private Long seq;

    private String title;

    private String content;

    @Column(insertable=false, updatable=false,
            columnDefinition = "timestamp default current_timestamp") //create 할 때 적용
    private Date createDate;

    @Column(insertable=false, updatable=true,
            columnDefinition = "int default 0")
    private int cnt;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    Member member;

    public Board() {

    }


    public void setMember(Member member){
        this.member = member;
        member.getBoardList().add(this);
    }

}
