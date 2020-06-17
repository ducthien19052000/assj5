package com.assj5.thien.assj5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/manager")
    public String adminHome(){
        return "admin/HomeAdmin";
    }
}
