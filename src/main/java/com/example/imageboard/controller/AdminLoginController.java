package com.example.imageboard.controller;

import com.example.imageboard.model.LoginCredentials;
import com.example.imageboard.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
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

    record AdminLoginData(String name, String password){
    }

    @PostMapping(value= "/m/login-post")
    public String adminLogin(AdminLoginData adminLoginData){
        LoginCredentials loginCredentials = loginRepository.findById(2).get();
            hashedAdminPassword = loginCredentials.getEncodedPassword();

//        loginCredentials.setEncodedPassword(passwordEncoder.encode(adminLoginData.password));
//        loginRepository.saveAndFlush(loginCredentials);

        if(compareHashedPasswordsResult(passwordEncoder.encode(adminLoginData.password),
                e -> passwordEncoder.matches(adminLoginData.password, e))){
            //TODO
        }
        return "redirect:/m";
    }

    private <T> boolean compareHashedPasswordsResult(T input, Predicate<T> predicate){
        return predicate.test(input);
    }

}
