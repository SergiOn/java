package org.annotationprocessing.pizzastorehandwritten;

public class MealFactoryHandWritten {

    public Meal create(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null!");
        }
        if ("Calzone".equals(id)) {
            return new CalzonePizza();
        }

        if ("Tiramisu".equals(id)) {
            return new Tiramisu();
        }

        if ("Margarita".equals(id)) {
            return new MargaritaPizza();
        }

        throw new IllegalArgumentException("Unknown id = " + id);
    }
}
