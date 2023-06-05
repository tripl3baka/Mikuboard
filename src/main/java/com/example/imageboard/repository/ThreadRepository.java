package com.example.imageboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.imageboard.model.Thread;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ThreadRepository extends JpaRepository<Thread, Integer>{

    @Query("SELECT COUNT(t.id) FROM Thread t WHERE t.id > :threadID")
    int CountThreadsWithIdHigherThan(@Param("threadID")Integer id);

}