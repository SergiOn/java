package com.example;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepository {

    List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
        this.users.add(new User(1, "Mario", "secret"));
        this.users.add(new User(2, "Ralf", "secret-1"));
        this.users.add(new User(3, "John", "secret-2"));
    }

    public boolean isAuthorized(User user){
        for (User u: users){
            if(u.username.equals(user.username) &&
                    u.password.equals(user.password)){
                return  true;
            }
        }

        return false;
    }
}
