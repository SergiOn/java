package com.example;

import lombok.Data;

@Data
public class Friend {

    Integer id;

    String name;

    public Friend(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
