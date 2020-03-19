package org.di.dependency.injection;

import org.di.dependency.holder.DependencyHolder;
import org.di.dependency.holder.DependencyInstanceHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class DependencyInjectionImpl implements DependencyInjection {

    private final DependencyHolder dependencyHolder;
    private final DependencyInstanceHolder dependencyInstanceHolder;

    public DependencyInjectionImpl(DependencyHolder dependencyHolder, DependencyInstanceHolder dependencyInstanceHolder) {
        this.dependencyHolder = dependencyHolder;
        this.dependencyInstanceHolder = dependencyInstanceHolder;
    }

    @Override
    public <K> void registerDependencyClass(Class<K> key, Class<? extends K> value) {
        dependencyHolder.saveDependency(key, value);
    }

    @Override
    public <K> K getDependencyInstance(Class<K> key) {
        K dependency = dependencyInstanceHolder.loadDependency(key);
        return dependency != null
                ? dependency
                : instantiateAndGetDependencyInstance(key);
    }

    @SuppressWarnings("unchecked")
    private <K> K instantiateAndGetDependencyInstance(Class<K> key) {
        Class<? extends K> clazz = dependencyHolder.loadDependency(key);
        Constructor<?>[] constructors = clazz.getConstructors();

        Constructor<?> constructor = Arrays.stream(constructors)
                .filter(c -> c.getParameterCount() == 0 || Arrays.stream(c.getParameterTypes()).allMatch(dependencyHolder::isDependencyExist))
                .reduce((acc, c) -> c.getParameterCount() > 0 ? c : acc)
                .orElseThrow();

        Class<?>[] parameterTypes = constructor.getParameterTypes();

        Object[] instantiatedDependencies = Arrays.stream(parameterTypes)
                .map(this::getDependencyInstance)
                .toArray();

//        Object[] instantiatedDependencies = Arrays.stream(constructor.getParameterTypes())
//                .map(this::getDependencyInstance)
//                .toArray();

        K instance;
        try {
            instance = (K) constructor.newInstance(instantiatedDependencies);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception with instantiating constructor: " + constructor.getName());
        }

        dependencyInstanceHolder.saveDependency(key, instance);
        return instance;
    }

}
