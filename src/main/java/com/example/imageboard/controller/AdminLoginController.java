package com.example.imageboard.controller;

import com.example.imageboard.model.LoginCredentials;
import com.example.imageboard.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminLoginController {
    
    LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminLoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    record AdminLoginData(String name, String password){
    }

    @PostMapping(value= "/m/login-post")
    public String adminLogin(AdminLoginData adminLoginData){
        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setEncodedPassword(passwordEncoder.encode(adminLoginData.password));
        loginRepository.saveAndFlush(loginCredentials);

        return "redirect:/m";
    }


}
