package com.example.imageboard.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private ZonedDateTime bumpedAt;

    public ZonedDateTime getBumpedAt() {
        return bumpedAt;
    }

    public void setBumpedAt(ZonedDateTime bumpedAt) {
        this.bumpedAt = bumpedAt;
    }

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

    public List<Reply> getRepliesWithImage() {
        List<Reply> listOfImages = new ArrayList<>();

        for (Reply reply : replies) {
            if (reply.getImgURL() != null && !reply.getImgURL().equals("")) {
                listOfImages.add(reply);
            }
        }
        return listOfImages;
    }

}
