package org.di.dependency.holder;

public interface DependencyHolder {
    public <K> void saveDependency(Class<K> key, Class<? extends K> value);
//    public <K> void saveDependency(String key, Class<? extends K> value);
    public <K> Class<? extends K> loadDependency(Class<K> key);
//    public <K> Class<? extends K> loadDependency(String key);
    public <K> boolean isDependencyExist(Class<K> key);
}
