package org.di.dependency.holder;

import org.di.dependency.holder.fixture.ADependency;
import org.di.dependency.holder.fixture.ADependencyImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
//import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class DependencyHolderImplTest {

    private DependencyHolder sut;
    private Map<Class<?>, Class<?>> dependencies;

    @BeforeEach
    public void beforeEach() {
        sut = new DependencyHolderImpl();
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
        sut.saveDependency(ADependency.class, ADependencyImpl.class);

        assertThat(dependencies, aMapWithSize(1));
        assertThat(dependencies, hasEntry(ADependency.class, ADependencyImpl.class));
    }

    @Test
    public void loadDependency() {
        dependencies.put(ADependency.class, ADependencyImpl.class);

        assertThat(dependencies, aMapWithSize(1));
        assertEquals(sut.loadDependency(ADependency.class), ADependencyImpl.class);
    }

    @Test
    void isDependencyExist() {
        assertFalse(sut.isDependencyExist(ADependency.class));

        dependencies.put(ADependency.class, ADependencyImpl.class);

        assertTrue(sut.isDependencyExist(ADependency.class));
    }

}
