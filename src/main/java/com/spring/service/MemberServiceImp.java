package com.spring.service;

import com.spring.domain.Member;
import com.spring.domain.Role;
import com.spring.exception.BadRequestException;
import com.spring.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberServiceImp implements MemberService {

    MemberRepository memberRepo;

    PasswordEncoder passwordEncoder;

    @Override
    public Member getMember(Member member) {

        Optional<Member> member1 = memberRepo.findById(member.getId());

        return member1.orElse(null);//orElse 값이 null 이면 인자로 전달한 other 반환
    }


    @Override
    public Member memberJoin(Member member) {

        memberRepo.findById(member.getId()).ifPresent(action -> {
            throw new BadRequestException("이미 존재하는 회원입니다.");
        });

        String encoded = passwordEncoder.encode(member.getPassword());
        member.setPassword(encoded);

        member.setRole(Role.ROLE_USER);

        memberRepo.save(member);

        Optional<Member> member1 = memberRepo.findById(member.getId());
        return member1.orElseThrow(() -> new NoSuchElementException());
    }


    @Override
    public void updateMember(Member member) {

        if (memberRepo.findById(member.getId()).isPresent()) {
            Member member1 = memberRepo.findById(member.getId()).get();
            member1.setName(member.getName());
            memberRepo.save(member1);
        } else {
            throw new RuntimeException();
        }

    }


    @Override
    public boolean checkId(Member member) {
        return memberRepo.findById(member.getId()).isPresent();
    }
}
