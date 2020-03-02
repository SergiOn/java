package com.virtualpairprogrammers.tautology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NumberValidatorTests {

	@Test
	public void checkPrimeNumbers()
	{
		
		Integer numbers[] = {1,23,61,79};
		NumberValidator validator = new NumberValidator();
		
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(true, validator.isItPrime(numbers[i]));
		}
		
	}
	
	@Test
	public void checkNonPrimeNumbers()
	{
		
		Integer numbers[] = {15,25,60,63,207};
		NumberValidator validator = new NumberValidator();
		
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(false, validator.isItPrime(numbers[i]));
		}
		
	}

}
