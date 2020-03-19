package org.di.dependency.holder;

import org.di.dependency.holder.fixture.ADependency;
import org.di.dependency.holder.fixture.ADependencyImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class DependencyInstanceHolderImplTest {

    private DependencyInstanceHolder sut;
    private Map<Class<?>, Object> dependencies;

    @BeforeEach
    public void beforeEach() {
        sut = new DependencyInstanceHolderImpl();
        dependencies = Whitebox.getInternalState(sut, "dependencies");
    }

    @Test
    public void dependenciesHasCorrectType() {
        assertThat(dependencies, instanceOf(Map.class));
    }

    @Test
    public void dependenciesHasCorrectDefaultSize() {
        assertThat(dependencies, anEmptyMap());
    }

    @Test
    public void saveDependency() {
        ADependency aDependency = new ADependencyImpl();
        sut.saveDependency(ADependency.class, aDependency);

        assertThat(dependencies, aMapWithSize(1));
        assertThat(dependencies, hasEntry(ADependency.class, aDependency));
    }

    @Test
    public void loadDependency() {
        ADependency aDependency = new ADependencyImpl();
        dependencies.put(ADependency.class, aDependency);

        assertThat(dependencies, aMapWithSize(1));
        assertEquals(sut.loadDependency(ADependency.class), aDependency);
    }

    @Test
    void isDependencyExist() {
        assertFalse(sut.isDependencyExist(ADependency.class));

        ADependency aDependency = new ADependencyImpl();
        dependencies.put(ADependency.class, aDependency);

        assertTrue(sut.isDependencyExist(ADependency.class));
    }

}
