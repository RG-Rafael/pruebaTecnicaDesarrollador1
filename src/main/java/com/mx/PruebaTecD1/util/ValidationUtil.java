package com.mx.PruebaTecD1.util;

public class ValidationUtil {

	public static boolean isValidRFC(String rfc){

        String regex = "^[A-Z]{4}[0-9]{6}[A-Z0-9]{3}$";

        return rfc != null && rfc.matches(regex);
    }
	
	public static boolean isValidPhone(String phone){

	    String regex = "^(\\+\\d{1,3})?\\s?\\d{10}$";

	    return phone != null && phone.matches(regex);
	}
}
