package org.di.examples;

public class UserServiceImpl implements UserService {

    @Override
    public String getUserName() {
        return "User name";
    }

    public String aMethod() {
        return "A";
    }

    public String bMethod() {
        return "B";
    }

}
