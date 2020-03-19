package org.di.examples;

public class ADependencyImpl implements ADependency {

    private BDependency bDependency;

    public ADependencyImpl() {}

    public ADependencyImpl(String s) {}

    public ADependencyImpl(BDependency bDependency) {
        this.bDependency = bDependency;
    }

    @Override
    public String getName() {
//        return "ADependencyImpl implements ADependency";
        return "ADependencyImpl implements ADependency" + " ----- " + bDependency.getName();
    }
}
