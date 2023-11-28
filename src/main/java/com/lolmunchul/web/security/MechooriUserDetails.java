package com.lolmunchul.web.security;

import com.lolmunchul.web.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MechooriUserDetails implements UserDetails {

    private Member member;
    private Map<String,Object> attributes;
    private List<GrantedAuthority> authorities;

    //일반 회원
    public MechooriUserDetails(Member member) {
        this.member = member;
    }

    //소셜 회원
    public MechooriUserDetails(Member member, Map<String,Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    public int getId() {
        return member.getId();
    }


    public String getEmail() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}