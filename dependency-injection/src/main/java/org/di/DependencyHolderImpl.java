package org.di;

import java.util.HashMap;
import java.util.Map;

public class DependencyHolderImpl implements DependencyHolder {

    final private Map<Class<?>, Class<?>> map = new HashMap<>();

    @Override
    public <T> void registerDependency(Class<T> clazzName, Class<? extends T> clazz) {
        map.put(clazzName, clazz);
    }

    @Override
    public <T> Class<? extends T> loadDependency(Class<T> clazzName) {
        return map.get(clazzName).asSubclass(clazzName);
    }

}
