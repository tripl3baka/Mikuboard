package com.example.imageboard.repository;
import com.example.imageboard.model.Reply;
import com.example.imageboard.model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

//    @Query("SELECT r FROM Reply r WHERE r.thread = :threadID")
//    List<Reply> findRepliesBelongingToGivenThread(@Param("threadID")Integer id);
}
