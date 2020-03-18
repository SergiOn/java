package org.di;

public interface DependencyHolder {

    public <T> void registerDependency(Class<T> clazzName, Class<? extends T> clazz);
    public <T> Class<? extends T> loadDependency(Class<T> clazzName);

}
