package com.example.imageboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.imageboard.model.Thread;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ThreadRepository extends JpaRepository<Thread, Integer>{

}