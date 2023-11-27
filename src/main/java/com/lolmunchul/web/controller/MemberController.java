package com.lolmunchul.web.controller;


import com.lolmunchul.web.entity.Member;
import com.lolmunchul.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("/join")
    public String join(Model model){

        List<Member> list = service.getList();
        model.addAttribute("list", list);

        return "user/join";
    }

}
