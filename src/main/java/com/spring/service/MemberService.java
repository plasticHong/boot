package com.spring.service;

import com.spring.domain.Member;

public interface MemberService {
    Member getMember(Member member);  //로그인

    Member memberJoin(Member member);

    void updateMember(Member member);
}
