package com.virtualpairprogrammers.tautology;
import static org.junit.Assert.*;

import org.junit.Test;


public class NumberValidatorTests {

//	@Test
//	public void checkPrimeNumbers()
//	{
//		
//		Integer numbers[] = {1,15,23,25,60,61,63,79,207};
//		NumberValidator validator = new NumberValidator();
//		
//		for (int i = 0; i < numbers.length; i++) {
//			boolean isPrime = true;
//			int maxDivisor = (int)Math.sqrt(numbers[i]); 
//			for(int counter =2;counter < maxDivisor; counter ++) {
//		        if(numbers[i] % counter ==0)
//		        	isPrime = false;
//		    }
//				
//		assertEquals(isPrime, validator.isItPrime(numbers[i]));
//		
//		}
//		
//	}
	
	@Test
	public void checkPrimeNumbers() 
	{
		Integer numbers[] = {1,23,61,79};
		
		NumberValidator validator = new NumberValidator();
		
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(true,validator.isItPrime(numbers[i]));
		}
		
	}
	
	
	@Test
	public void checkNonPrimeNumbers() 
	{
		Integer numbers[] = {15,25,60,63,207};
		
		NumberValidator validator = new NumberValidator();
		
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(false,validator.isItPrime(numbers[i]));
		}
	}
	
	

}
