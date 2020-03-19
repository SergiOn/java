package org.di;

import org.di.dependency.holder.DependencyHolder;
import org.di.dependency.holder.DependencyHolderImpl;
import org.di.dependency.holder.DependencyInstanceHolder;
import org.di.dependency.holder.DependencyInstanceHolderImpl;
import org.di.dependency.injection.DependencyInjection;
import org.di.dependency.injection.DependencyInjectionImpl;
import org.di.examples.*;

public class Main {

    public static void main(String[] args) {
        DependencyHolder dependencyHolder = new DependencyHolderImpl();
        DependencyInstanceHolder dependencyInstanceHolder = new DependencyInstanceHolderImpl();
        DependencyInjection dependencyInjection = new DependencyInjectionImpl(dependencyHolder, dependencyInstanceHolder);

        dependencyInjection.registerDependencyClass(ADependency.class, ADependencyImpl.class);
        dependencyInjection.registerDependencyClass(BDependency.class, BDependencyImpl.class);
        dependencyInjection.registerDependencyClass(UserService.class, UserServiceImpl.class);

        System.out.println("----- ----- -----");
        System.out.println("----- ----- before (start) ----- -----");
        System.out.println("----- ----- -----");
        System.out.println("----- ----- dependencyHolder ----- -----");
        System.out.println(dependencyHolder.loadDependency(ADependency.class));
        System.out.println(dependencyHolder.loadDependency(UserService.class));
        System.out.println("----- ----- DependencyInstanceHolder ----- -----");
        System.out.println(dependencyInstanceHolder.loadDependency(ADependency.class));
        System.out.println(dependencyInstanceHolder.loadDependency(UserService.class));
        System.out.println("----- ----- -----");
        System.out.println("----- ----- before (end) ----- -----");
        System.out.println("----- ----- -----");

        UserService userService = dependencyInjection.getDependencyInstance(UserService.class);
        System.out.println("----- ----- -----");
        System.out.println(userService);
        System.out.println(userService.getUserName());
        System.out.println(userService.getDependency1Name());
        System.out.println(userService.getDependency2Name());
        System.out.println(new UserServiceImpl(new ADependencyImpl(), new ADependencyImpl()));
        System.out.println("----- ----- -----");

        System.out.println("----- ----- -----");
        System.out.println("----- ----- after (start) ----- -----");
        System.out.println("----- ----- -----");
        System.out.println("----- ----- dependencyHolder ----- -----");
        System.out.println(dependencyHolder.loadDependency(ADependency.class));
        System.out.println(dependencyHolder.loadDependency(UserService.class));
        System.out.println("----- ----- DependencyInstanceHolder ----- -----");
        System.out.println(dependencyInstanceHolder.loadDependency(ADependency.class));
        System.out.println(dependencyInstanceHolder.loadDependency(ADependency.class));
        System.out.println(dependencyInstanceHolder.loadDependency(UserService.class));
        System.out.println(dependencyInstanceHolder.loadDependency(UserService.class));
        System.out.println("----- ----- -----");
        System.out.println("----- ----- after (end) ----- -----");
        System.out.println("----- ----- -----");

    }

}
