package org.di;

import org.di.examples.UserService;
import org.di.examples.UserServiceImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        DependencyHolderImpl dependencyHolder = new DependencyHolderImpl();
        dependencyHolder.registerDependency(UserService.class, UserServiceImpl.class);
        Class<?> userServiceClass = dependencyHolder.loadDependency(UserService.class);

//        Optional.ofNullable(null).stream()

        Constructor<?>[] declaredConstructors = userServiceClass.getDeclaredConstructors();
        UserService userService = (UserService) Arrays.stream(declaredConstructors)
                .filter(constructor -> constructor.getGenericParameterTypes().length == 0)
                .map(constructor -> {
                    try {
                        return constructor.newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .findFirst()
                .orElse(null);

        System.out.println(userService.getUserName());

        var arr = new ArrayList<String>();
        arr.add("1");
        arr.add("2");
        arr.add("3");

        for (int i = 0; i < arr.size(); i++) {
//            System.out.println(arr.get(i));
//            arr.add(String.valueOf(i + 4));
        }

//        arr.forEach(i -> {
//            System.out.println(i);
//            arr.add(i + i);
//        });

//        for (String i : arr) {
//            System.out.println(i);
//            arr.add(i + i);
//        }

    }

}
