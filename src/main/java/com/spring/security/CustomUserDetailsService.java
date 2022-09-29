package com.spring.security;

import com.spring.domain.Member;
import com.spring.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {


    MemberRepository memberRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> byId = memberRepo.findById(username);

        if(byId.isEmpty()){
            throw new UsernameNotFoundException(username);
        }else{
            Member member = byId.get();
            return new SecurityUser(member);
        }


    }

}
