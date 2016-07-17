package com.java.oracle.factory;

import com.java.oracle.generator.IBNGenerator;

public class IBNGeneratorFactory {

	public static String genereateIBN(String countryCode){
		synchronized (countryCode) {
			String ibn=null;
			
			if(null!=countryCode && ("NL".equals(countryCode) || "DE".equals(countryCode) || "AT".equals(countryCode) ))
			   ibn= (new IBNGenerator()).generateIBN(countryCode);
			else
				return "No Proper Country Code";
			return ibn;
		}
		
	}
}
