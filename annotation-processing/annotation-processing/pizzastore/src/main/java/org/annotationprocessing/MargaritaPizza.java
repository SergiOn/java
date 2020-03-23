package org.annotationprocessing;

import org.annotationprocessing.annotation.Factory;

@Factory(
        id = "Margarita",
        type = Meal.class
)
public class MargaritaPizza implements Meal {

    @Override
    public float getPrice() {
        return 6f;
    }
}
