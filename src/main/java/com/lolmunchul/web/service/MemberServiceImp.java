package com.lolmunchul.web.service;


import com.lolmunchul.web.entity.Member;
import com.lolmunchul.web.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
