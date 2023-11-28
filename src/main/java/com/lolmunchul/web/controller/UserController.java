package com.lolmunchul.web.controller;


import com.lolmunchul.web.entity.Member;
import com.lolmunchul.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private MemberService service;

    @GetMapping("user/join")
    public String form() {
        return "user/join";
    }
    @PostMapping("user/join")
    public String join(Member member){
        service.add(member);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @PostMapping("/login")
    public String afterLogin() {

        return "redirect:/admin";
    }

    @GetMapping("admin")
    public String admin() {
        return "admin";
    }
}
