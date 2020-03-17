package com.virtualpairprogrammers.tautology;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberValidatorTest {

    @Test
    public void checkPrimeNumbers() {
        Integer[] numbers = {23, 61, 79};
        NumberValidator numberValidator = new NumberValidator();

        for (Integer number : numbers) {
            assertTrue(numberValidator.isItPrime(number));
        }
    }

    @Test
    public void checkNonPrimeNumbers() {
        Integer[] numbers = {1, 15, 25, 60, 63, 207};
        NumberValidator numberValidator = new NumberValidator();

        for (Integer number : numbers) {
            assertFalse(numberValidator.isItPrime(number));
        }
    }

}
