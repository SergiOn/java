package org.di.dependency.holder;

import java.util.HashMap;
import java.util.Map;

public class DependencyHolderImpl implements DependencyHolder {

    private final Map<Class<?>, Class<?>> dependencies = new HashMap<>();

    @Override
    public <K> void saveDependency(Class<K> key, Class<? extends K> value) {
        dependencies.put(key, value);
    }

    @Override
    public <K> Class<? extends K> loadDependency(Class<K> key) {
        return dependencies.get(key).asSubclass(key);
    }

    @Override
    public <K> boolean isDependencyExist(Class<K> key) {
        return dependencies.containsKey(key);
    }

}
