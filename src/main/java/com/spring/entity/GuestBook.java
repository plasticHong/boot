package com.spring.entity;


import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class GuestBook extends BaseEntity{

    @Id
    @GeneratedValue
    private Long gno;
    @Column(length = 100,nullable = false)
    private String title;
    @Column(length = 1500,nullable = false)
    private String content;
    @Column(length = 50,nullable = false)
    private String writer;

    public GuestBook() {
    }
}
