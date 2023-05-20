package com.example.imageboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.imageboard.model.Thread;

public interface ThreadRepository extends JpaRepository<Thread, Integer> {
}
