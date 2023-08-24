package util;

import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtils {
	
	public static BigInteger encrypt (String number) throws NoSuchAlgorithmException {
		
		MessageDigest md5;
		BigInteger passwordCript = null;
		
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(number.getBytes(), 0, number.length());
			passwordCript = new BigInteger(1, md5.digest());
			
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		return passwordCript;
		
	}
	
}
