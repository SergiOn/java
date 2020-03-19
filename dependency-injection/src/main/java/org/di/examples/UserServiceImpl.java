package org.di.examples;

public class UserServiceImpl implements UserService {

    public final String name = "User name";
    public ADependency aDependency1;
    public ADependency aDependency2;

    public UserServiceImpl() {
    }

    public UserServiceImpl(ADependency aDependency1, ADependency aDependency2) {
        this.aDependency1 = aDependency1;
        this.aDependency2 = aDependency2;
    }

    public UserServiceImpl(String s1, String s2, String s3) {
    }

    public UserServiceImpl(String s1, String s2, String s3, String s4) {
    }

    private UserServiceImpl(String name, String some) {
        System.out.println("--------------------" + name + "--------------------");
        System.out.println("--------------------" + some + "--------------------");
    }

    @Override
    public String getUserName() {
        return name;
    }

    @Override
    public String getDependency1Name() {
        return aDependency1.getName();
    }

    @Override
    public String getDependency2Name() {
        return aDependency2.getName();
    }

    @Override
    public String aMethod() {
        return "A";
    }

    @Override
    public String bMethod() {
        return "B";
    }

}
