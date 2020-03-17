package com.virtualpairprogrammers.tautology;

public class NumberValidator {

    public boolean isItPrime(int number) {
        if (number < 2) {
            return false;
        }
        int maxDivisor = (int) Math.sqrt(number);
        for (int i = 2; i <= maxDivisor; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
