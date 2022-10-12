package com.spring.domain;

import javax.persistence.*;

import lombok.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "boardList")
@Entity
//@Builder
@AllArgsConstructor
public class Member implements Serializable {

    private static final long serialVersionUID = 5L;

    @Id
    @Column(name="member_id")
    private String id;

    private String password;
    private String name;

    @Column(insertable=true, updatable=true, nullable = true
            ) //create 할 때 적용
    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Board> boardList = new ArrayList<>();


    public Member() {

    }
}