package com.example.imageboard.controller;

import com.example.imageboard.model.LoginCredentials;
import com.example.imageboard.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminLoginController {
    @GetMapping("/m/user-login")
    public String loginFormDisplay(){
        return "adminLogin";
    }
}
