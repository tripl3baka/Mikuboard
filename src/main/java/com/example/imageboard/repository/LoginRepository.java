package com.example.imageboard.repository;
import com.example.imageboard.model.LoginCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginCredentials, Integer> {

    Optional<LoginCredentials> findByName(String username);
}
