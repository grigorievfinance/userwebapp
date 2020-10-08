package org.softmaker.userwebapp.web;

import org.softmaker.userwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class  RootController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public String root(){
        return "redirect:users";
    }

    @GetMapping("/users")
    public String getUsers(){
        return "users";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
