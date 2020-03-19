package org.di.dependency.injection;

public interface DependencyInjection {
    public <K> void registerDependencyClass(Class<K> key, Class<? extends K> value);
    public <K> K getDependencyInstance(Class<K> key);
}
