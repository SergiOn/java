package com.example;

import lombok.Data;

@Data
public class User {

    Integer id;

    String username;

    String password;

    public User(){};

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
