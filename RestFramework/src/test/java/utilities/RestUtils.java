package utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String userid() {

		String generatedString = RandomStringUtils.randomNumeric(1);
		return (generatedString);

	}

	public static String iD() {
	
	   String generatedString = RandomStringUtils.randomNumeric(2);
		return(generatedString);

	}

	public static String title() {
		
		String generatedString = RandomStringUtils.randomAlphabetic(7);
		return(generatedString);
		
	}
	
	public static String body() {
		
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return(generatedString);
		
	}
	
}
