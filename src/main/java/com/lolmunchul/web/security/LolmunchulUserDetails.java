package com.lolmunchul.web.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.lolmunchul.web.entity.Member;

public class LolmunchulUserDetails implements UserDetails, OAuth2User {

    private Member member;
    private Map<String,Object> attributes;
    private List<GrantedAuthority> authorities;

    //일반 회원
    public LolmunchulUserDetails(Member member) {
        this.member = member;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    public int getId() {
        return member.getId();
    }

    public String getNickname() {
        return member.getNickname();
    }
    public void setNickname(String nickname){
        this.member.setNickname(nickname);
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