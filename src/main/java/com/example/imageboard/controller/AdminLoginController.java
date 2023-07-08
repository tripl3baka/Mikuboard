package com.example.imageboard.controller;

import com.example.imageboard.model.LoginCredentials;
import com.example.imageboard.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminLoginController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/m/user-login")
    public String loginFormDisplay(){
        //System.out.println(passwordEncoder.encode("123"));
        LoginCredentials admin = loginRepository.findByName("admin");
        System.out.println(admin.getName());
        System.out.println(admin.getEncodedPassword());
        return "login";
    }

}
