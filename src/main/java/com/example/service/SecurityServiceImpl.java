package com.example.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dto.CustomUser;
import com.example.dto.Member;
import com.example.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityServiceImpl implements UserDetailsService {
    
    final String format = "SecurityServiceImpl => {}";
    final MemberMapper mMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       log.info(format,username);
        //아이디를 전달해서 정보를 받아옴(암호까지 받아옴)
        Member member = mMapper.selectMemberOne1(username);
        if(member != null){ //가져올 정보가 있음(=존재하는 아이디가 있음) / 암호가 일치하는지는 모름 User securitycore에 있음
            //Member DTO를 사용해서 처리하나 시큐리티는 User DTO를 사용함
            //세션에는 User타입으로 저장됨

            //User를 이용할 경우(세션내용 아이디, 암호 , 권한)
            // return User.builder().username(member.getId())
            //         .password(member.getPassword())
            //         .roles(member.getRole())
            //         .build();

            //CustomUser을 사용할 경우(세션내용 => 아이디, 암호, 권한, 이름, 나이)
            String[] strRole={"ROLE_" + member.getRole()};
            Collection<GrantedAuthority> role = AuthorityUtils.createAuthorityList(strRole);
            return new CustomUser(member.getId() ,member.getPassword() ,  role, member.getName(), member.getAge());
        }
        //존재하지 않는 아이디
            return User.builder().username("_")
                    .password("_")
                    .roles("_")
                    .build(); 
    }

}
