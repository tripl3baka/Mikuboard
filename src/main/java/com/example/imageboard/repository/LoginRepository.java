package com.example.imageboard.repository;

import com.example.imageboard.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Admin, Integer> {
}
