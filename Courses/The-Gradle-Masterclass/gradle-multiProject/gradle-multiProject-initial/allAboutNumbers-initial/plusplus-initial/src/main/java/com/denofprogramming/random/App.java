package com.denofprogramming.random;

import org.apache.commons.math3.random.RandomDataGenerator;

/**
 *
 * 
 */
public class App {

	public static void main(String[] args) {

		RandomGenerator aRandomGenerator = new DefaultRandomGenerator();
		System.out.println("The number is :" + aRandomGenerator.generate());
		System.out.println("The 2nd number is :" + generateMy());

	}

	public static int generateMy() {
		final RandomDataGenerator aRandomDataGenerator = new RandomDataGenerator();
		return aRandomDataGenerator.nextInt(5, 10);
	}

}
