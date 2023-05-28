package com.example.imageboard.model;

import jakarta.persistence.*;
import java.util.List;


@Entity
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    @OneToMany(mappedBy = "thread")
    private List<Reply> replies;

    public void setId(Integer id) {
        this.id = id;
    }

    public Thread() {
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
