package com.spring.security;

import com.spring.domain.Member;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser  extends User {   //User 는 UserDetails 의 구현체

    //시큐리티가 해석가능한 객체로 만듦

    private static final long serialVersionUID = 1L;

    public SecurityUser(Member member) {
        super(member.getId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
    }
}
