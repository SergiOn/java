package com.example.rest;

import lombok.Data;

@Data
public class User {

    Integer id;

    String username;

    String password;

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
