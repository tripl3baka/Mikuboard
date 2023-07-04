package com.example.imageboard.repository;
import com.example.imageboard.model.LoginCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface LoginRepository extends JpaRepository<LoginCredentials, Integer> {

    LoginCredentials findByName(String name);
}
