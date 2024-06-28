package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entity.User;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.forms.UserForm;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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

    @GetMapping("/regi")
    public String register(Model model) {
        
        System.out.println("Registraion page called");
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        
        return "register";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String requestMethodName(@Valid @ModelAttribute UserForm userForm, BindingResult result, HttpSession session)
            throws InterruptedException {

        
        System.out.println("Inside from form submit method");
        System.out.println(userForm);

        if (result.hasErrors()) {
            return "register";
        }

        System.out.println("Inside from form submit method");

        User user = new User();

        user.setEmail(userForm.getEmail());
        user.setName(userForm.getName());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://avatars.githubusercontent.com/u/124692775?v=4");
        user.setAbout(userForm.getAbout());
        user.setPassword(userForm.getPassword());

        // to show message
        Message toShow = Message.builder().content("Registration Successful !").messageType(MessageType.green).build();

        // Add message in session
        session.setAttribute("message", toShow);

        return "login";

    }

}
