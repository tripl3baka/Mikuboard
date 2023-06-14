package com.example.imageboard.model;

import jakarta.persistence.*;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 65534, columnDefinition = "TEXT")
    private String name;

    @Column(length = 65534, columnDefinition = "TEXT")
    private String encodedPassword;

    public Admin() {
    }

    public Admin(String name, String encodedPassword, Integer id) {
        this.id = id;
        this.name = name;
        this.encodedPassword = encodedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
