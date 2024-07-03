package com.demo.Utils;

import org.apache.commons.lang3.RandomStringUtils;

public class PwdUtils {

	public static String generateRandomPwd() {
		
		String characters = "ABCDEFG";
		String pwd = RandomStringUtils.random( 3, characters );
		//System.out.println( pwd );	
		
		
		return pwd;
		
	}
}
