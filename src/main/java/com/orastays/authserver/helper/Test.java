package com.orastays.authserver.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {

		try {
			System.out.println(checkEmail("avirup"));
			System.out.println(checkEmail("avirup.pal@gmail.com"));
		} catch (Exception e) {

		}

	}

	public static boolean checkEmail(String emailId) {

		String emailPattern = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(emailId);
		return matcher.matches();
	}

}
