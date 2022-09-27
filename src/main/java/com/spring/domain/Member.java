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

    @Column(insertable=false, updatable=true, nullable = false,
            columnDefinition = "varchar default ROLE_ADMIN") //create 할 때 적용
    private String role;

    @OneToMany(mappedBy = "member",fetch = FetchType.EAGER)
    private List<Board> boardList = new ArrayList<>();
}