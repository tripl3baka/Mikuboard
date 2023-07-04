package com.example.imageboard.configuration;

import com.example.imageboard.repository.LoginRepository;
import com.example.imageboard.service.AdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/**")
                        .permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/m/login")
                        .permitAll()
                );
//                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new AdminDetailsService();
    }
}