package com.example.imageboard.repository;
import com.example.imageboard.model.LoginCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginCredentials, Integer> {
}
