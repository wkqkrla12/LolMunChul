package com.lolmunchul.web.service;


import com.lolmunchul.web.entity.Member;
import com.lolmunchul.web.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImp implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Override
    public List<Member> getList() {
        return repository.findAll();
    }

    @Override
    public void add(Member member) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        repository.save(member);
    }

    @Override
    public Member getByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public String getRoleByEmail(String email) {
        return null;
    }
}
