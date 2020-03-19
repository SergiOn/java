package org.di.dependency.holder;

import java.util.HashMap;
import java.util.Map;

public class DependencyInstanceHolderImpl implements DependencyInstanceHolder {

    private final Map<Class<?>, Object> dependencies = new HashMap<>();

    @Override
    public <K, V extends K> void saveDependency(Class<K> key, V value) {
        dependencies.put(key, value);
    }

    @Override
    public <K> K loadDependency(Class<K> key) {
        return (K) dependencies.get(key);
    }

    @Override
    public <K> boolean isDependencyExist(Class<K> key) {
        return dependencies.containsKey(key);
    }

}
