package com.example.imageboard.configuration;

import com.example.imageboard.component.AuthProvider;
import com.example.imageboard.service.AdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Autowired
    private AdminDetailsService adminDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/**")
                        .permitAll()

                )
                .formLogin((form) -> form
                        .loginPage("/m/user-login")
                        .defaultSuccessUrl("/m")
                        .permitAll()
                        .loginProcessingUrl("/m/login")
                        .passwordParameter("password")
                        .usernameParameter("username")
                )
                .anonymous((AbstractHttpConfigurer::disable));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return adminDetailsService;
    }
}