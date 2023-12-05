package com.lolmunchul.web.security;

import com.lolmunchul.web.entity.Member;
import com.lolmunchul.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LolmunchulUserDetailsService implements UserDetailsService{

    @Autowired
    MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberService.getByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException("Member not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        // username 통해서 db의 member_with_role (view)에서 role_name을 String role에 할당
        String role = memberService.getRoleByEmail(email);
        // 얻어온 role을 authorities 리스트에 추가
//        authorities.add(new SimpleGrantedAuthority(role));
//        String role = memberService.getRoleByEmail(email);

        if (StringUtils.hasText(role)) {
            authorities.add(new SimpleGrantedAuthority(role));
        } else {
            // 기본 권한을 부여하거나 다른 처리를 수행할 수 있습니다.
            authorities.add(new SimpleGrantedAuthority("ROLE_DEFAULT"));
        }

        com.lolmunchul.web.security.LolmunchulUserDetails userDetails = new LolmunchulUserDetails(member);
        userDetails.setAuthorities(authorities);
        return userDetails;
    }
}