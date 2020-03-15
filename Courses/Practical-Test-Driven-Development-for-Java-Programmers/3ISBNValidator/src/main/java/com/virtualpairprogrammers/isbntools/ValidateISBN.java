package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {

    private static final int SHORT_ISBN_LENGTH = 10;
    private static final int LONG_ISBN_LENGTH = 13;
    private static final int SHORT_ISBN_MULTIPLIER = 11;
    private static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean checkISBN(String isbn) {
        if (isbn.length() == LONG_ISBN_LENGTH) {
            return isThisAValidLongISBN(isbn);
        } else if (isbn.length() == SHORT_ISBN_LENGTH) {
            return isThisAValidShortISBN(isbn);
        } else {
            throw new NumberFormatException("ISBN numbers must be 10 digits long");
        }
    }

    private boolean isThisAValidShortISBN(String isbn) {
        int total = 0;

        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            char currentChar = isbn.charAt(i);

            if (!Character.isDigit(currentChar)) {

                if (i == 9 && currentChar == 'X') {
                    total += 10;
                } else {
                    throw new NumberFormatException("ISBN numbers can only contain numeric digit");
                }
            } else {
                total += Character.getNumericValue(currentChar) * (SHORT_ISBN_LENGTH - i);
            }
        }
        return total % SHORT_ISBN_MULTIPLIER == 0;
    }

    private boolean isThisAValidLongISBN(String isbn) {
        int total = 0;

        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            int currentInt = Character.getNumericValue(isbn.charAt(i));

            if (i % 2 == 0) {
                total += currentInt;
            } else {
                total += currentInt * 3;
            }
        }
        return total % LONG_ISBN_MULTIPLIER == 0;
    }

}
