package com.goparty.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserController {


    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping("overview.htm")
    public String overview(Model model) {

        return "user_overview";
    }


}