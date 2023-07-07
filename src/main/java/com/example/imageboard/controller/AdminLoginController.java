package com.example.imageboard.controller;

import com.example.imageboard.model.LoginCredentials;
import com.example.imageboard.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.function.Predicate;

@Controller
public class AdminLoginController {
    
    LoginRepository loginRepository;
    private String hashedAdminPassword;


    @Autowired
    private PasswordEncoder passwordEncoder;
    public AdminLoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    record LoginData(String name, String password){
    }
    @PostMapping(value="/m/login")
    public String login(){
        return"";
    }

    @GetMapping("/m/login")
    public String login3(){
        return"adminLogin";
    }

}
