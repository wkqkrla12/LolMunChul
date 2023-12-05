package com.lolmunchul.web.controller;


import com.lolmunchul.web.entity.Member;
import com.lolmunchul.web.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private MemberService service;

    @GetMapping("join")
    public String form() {
        System.out.println("Form method called");
        return "user/join";
    }
    @PostMapping("join")
    public String join(Member member){
        service.add(member);
        return "redirect:/";
    }

    @GetMapping("login")
    public String login(){
    return "user/login";
    }
    @PostMapping("login")
    public String afterLogin() {
        return "redirect:/";
    }


    @GetMapping("admin")
    public String admin() {
        return "admin";
    }
}
