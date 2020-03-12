package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {

    public boolean checkISBN(String isbn) {
        if (isbn.length() == 13) {
            int total = 0;

            for (int i = 0; i < 13; i++) {
                int currentInt = Character.getNumericValue(isbn.charAt(i));

                if (i % 2 == 0) {
                    total += currentInt;
                } else {
                    total += currentInt * 3;
                }
            }
            return total % 10 == 0;
        } else {
            if (isbn.length() != 10) {
                throw new NumberFormatException("ISBN numbers must be 10 digits long");
            }

            int total = 0;

            for (int i = 0; i < 10; i++) {
                char currentChar = isbn.charAt(i);

                if (!Character.isDigit(currentChar)) {

                    if (i == 9 && currentChar == 'X') {
                        total += 10;
                    } else {
                        throw new NumberFormatException("ISBN numbers can only contain numeric digit");
                    }
                } else {
                    total += Character.getNumericValue(currentChar) * (10 - i);
                }
            }
            return total % 11 == 0;
        }
    }

}
