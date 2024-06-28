package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class PageController {


    @GetMapping("/home")
    public String home() {
        return "home";
    }


    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/navbar")
    public String navbar() {
        return "navbar";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    
}
