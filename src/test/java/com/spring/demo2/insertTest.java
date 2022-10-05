package com.spring.demo2;


import com.spring.domain.Member;
import com.spring.domain.Role;
import com.spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootTest
public class insertTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository repository;


    @Test
    public void insertTest() {

        Member member = new Member();

        member.setId("cj");
        member.setPassword(passwordEncoder.encode("cj"));
        member.setName("carl johnson");
        member.setRole(Role.ROLE_USER);

        repository.save(member);

    }

    @Test
    public void defaultValTest(){

        Member member = new Member();

        member.setId("cj3");
        member.setPassword(passwordEncoder.encode("cj"));
        member.setName("carl johnson");
        member.setName("carl johnson");

        repository.save(member);
        Optional<Member> byId = repository.findById(member.getId());

        byId.ifPresent(a->{
            String role = String.valueOf(byId.get().getRole());

            System.out.println(role);
        });

    }



}
