package com.lolmunchul.web.service;


import com.lolmunchul.web.entity.Member;
import java.util.List;

public interface MemberService {
    List<Member> getList();

    void add(Member member);

    Member getByEmail(String email);

    String getRoleByEmail(String email);
}
