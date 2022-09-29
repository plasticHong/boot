package com.spring.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "boardList")
@Entity
public class Member {

    @Id
    @Column(name="member_id")
    private String id;

    private String password;
    private String name;

    @Column(insertable=true, updatable=true, nullable = true,
            columnDefinition = "varchar default ROLE_USER") //create 할 때 적용
    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;

    @OneToMany(mappedBy = "member",fetch = FetchType.EAGER)
    private List<Board> boardList = new ArrayList<>();
}