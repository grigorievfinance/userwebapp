package org.softmaker.userwebapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class  RootController {

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
