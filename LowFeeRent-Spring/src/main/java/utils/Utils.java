package utils;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {

	public static String generateId() {

		return RandomStringUtils.randomNumeric(7);

	}

	public static int randRange(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public static boolean getRandomBoolean() {
		Random random = new Random();
		return random.nextBoolean();
	}
	
	
	

}
