package com.assj5.thien.assj5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/register")
    public String register(){
        return "Register";
    }
    @GetMapping("/login")
    public String login(){
        return "LoginUser";
    }
}
