package com.lolmunchul.web.repository;


import com.lolmunchul.web.entity.Member;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRepository {
    List<Member> findAll();

    void save(Member member);
}
