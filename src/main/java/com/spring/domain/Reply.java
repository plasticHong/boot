package com.spring.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;

    private String content;

    private String writer;

    @ManyToOne
    private Board board;

    public Reply() {
    }
}
