package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateISBNTest {

    @Test
    public void checkAValidateISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
        assertTrue(result);

        result = validator.checkISBN("0140177396");
        assertTrue(result);
    }

    @Test
    public void checkAnInvalidateISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");

        assertFalse(result);
    }

}
