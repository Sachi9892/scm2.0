package com.scm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    

    //Dashboard page
    @GetMapping("user/dashboard")
    public String requestMethodName() {
        
        return "user/dashboard";

    }

    @GetMapping("user/profile")
    public String userProfile(Model model, Authentication authentication) {
        return new String("user/profile");
    }
    
}
