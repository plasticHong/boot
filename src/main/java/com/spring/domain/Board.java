package com.spring.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@DynamicInsert
@ToString(exclude = "boardList")
public class Board {

    @Id
    @GeneratedValue
    private Long seq;

    private String title;
    @Column(updatable=false)

    //private String writer;
    private String content;

    @Column(insertable=false, updatable=false,
            columnDefinition = "timestamp default current_timestamp") //create 할 때 적용
    private Date createDate;

    @Column(insertable=false, updatable=false,
            columnDefinition = "int default 0")
    private int cnt;

    @ManyToOne
    @JoinColumn(name = "member_id")
    Member member;


}
