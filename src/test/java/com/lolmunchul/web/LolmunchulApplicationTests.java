package com.lolmunchul.web;

import com.lolmunchul.web.entity.Member;
import com.lolmunchul.web.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class LolmunchulApplicationTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void saveMemberTest() {
        // given
        Member member = new Member();
        member.setEmail("test@example.com");
        member.setPassword("password");
        member.setRoleId(1);
        member.setRole("USER");
        member.setUsername("testuser");
        member.setNickname("Test User");

        // when
        memberRepository.save(member);

        // then
        Member foundMember = memberRepository.findByEmail("test@example.com");
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getEmail()).isEqualTo("test@example.com");
        assertThat(foundMember.getPassword()).isEqualTo("password");
        assertThat(foundMember.getRoleId()).isEqualTo(1);
        assertThat(foundMember.getRole()).isEqualTo("USER");
        assertThat(foundMember.getUsername()).isEqualTo("testuser");
        assertThat(foundMember.getNickname()).isEqualTo("Test User");
    }
}
