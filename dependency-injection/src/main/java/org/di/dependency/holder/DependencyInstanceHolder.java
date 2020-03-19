package org.di.dependency.holder;

import com.sun.jdi.Type;

public interface DependencyInstanceHolder {
    public <K, V extends K> void saveDependency(Class<K> key, V value);
    public <K> K loadDependency(Class<K> key);
    public <K> boolean isDependencyExist(Class<K> key);
}
