package org.di.examples;

public class BDependencyImpl implements BDependency {

    public BDependencyImpl() {}

    public BDependencyImpl(String s) {}

    @Override
    public String getName() {
        return "BDependencyImpl implements BDependency";
    }
}
