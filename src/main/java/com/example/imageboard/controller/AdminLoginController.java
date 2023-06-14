package com.example.imageboard.controller;

import com.example.imageboard.repository.LoginRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminLoginController {
    
    LoginRepository loginRepository;
    public AdminLoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    record AdminLoginData(String name, String password){
    }

    @PostMapping(value= "/m/login-post")
    public String adminLogin(AdminLoginData adminLoginData){
        return "redirect:/m";
    }

}
