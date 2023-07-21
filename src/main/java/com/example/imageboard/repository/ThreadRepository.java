package com.example.imageboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.imageboard.model.Thread;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ThreadRepository extends JpaRepository<Thread, Integer>{

    @Query("SELECT COUNT(t.id) FROM Thread t WHERE t.id > :threadID")
    int CountThreadsWithIdHigherThan(@Param("threadID")Integer id);

    @Query("SELECT t FROM Thread t WHERE t.isArchived = FALSE")
    Page<Thread> findThreadsNotArchived(Pageable pageable);

    @Query("SELECT COUNT(t.id) FROM Thread t WHERE t.isArchived = FALSE")
    int CountNotArchivedThreads();

    @Query("SELECT t FROM Thread t WHERE t.isArchived = FALSE")
    List<Thread> findThreadsNotArchived();


}