package com.lolmunchul.web.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal UserDetails userDetails) {

//        model.addAttribute("nickname", userDetails.getUsername());
//        System.out.println(model);

        return "index";
    }


}
