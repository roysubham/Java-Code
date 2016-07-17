package com.java.oracle.generator;

import java.util.Random;

public class IBNGenerator {
	
	
	
	public int mod97(String s){
		String temp="";
		int i,j,k,remTemp=0,finalRem=-1;
		long tempDigits;
		while(finalRem<0){
			k=1;
			tempDigits=0;
			remTemp=0;
			for(j=0;j<s.length() && k<=9;j++,k++){
				tempDigits=tempDigits*10+(s.charAt(j)-'0');
				
			}
			
			remTemp=(int)tempDigits%97;
			s=remTemp+s.substring(j);
			
			
			if(s.length()>2){
				continue;
			}
			else  {
				finalRem=remTemp;
				break;
			}
			
			
		}
		
		return finalRem;
	}
	public boolean validateIban(String s){
		s=s.replaceAll("\\s+","");
		String temp="";
		temp=s.substring(4)+s.substring(0, 4);
		temp=reverseStringWithNumbersAsDisgits(temp);
		if(mod97(temp)==1)
			return true;
		return false;
	}
	public String generateCheckDigits(String s){
		s=s.replaceAll("\\s+","");
		String temp="";
		int rem;
		rem=0;
		temp=s.substring(0,2)+"00"+s.substring(4);
		temp=reverseStringWithNumbersAsDisgits(temp);
		rem=mod97(temp);
		rem=98-rem;
		if(rem>9)
			return rem+"";
		else
			return "0"+rem;
	}
	public String reverseStringWithNumbersAsDisgits(String s){
		String temp=s;
		int remTemp=0,i;
		boolean flag;
		for( i=0;i<temp.length();){
			 flag=false;
			if(temp.charAt(i)==' ' ){
				continue;
			}
			if(temp.charAt(i)>='A' && temp.charAt(i)<='Z'){
				remTemp=temp.charAt(i)-'A'+10;
				flag=true;
			}
			else if(temp.charAt(i)>='a' && temp.charAt(i)<='z'){
				remTemp=temp.charAt(i)-'a'+10;
				flag=true;
			}
			if(flag){
				temp=temp.substring(0,i)+remTemp+temp.substring(i+1);
				i++;
			}
			if(i<temp.length())
				i++;
			else
				break;
		}
		return temp;
	}
	public String generateIBN(String countryCode){
		String iban="",checkDigits="";
		boolean flag=true;
		if(countryCode=="DE"){
			while(flag){
				iban="DE"+"00"+randomCode(6)+randomCode(2)+randomCode(8)+randomCode(2);
				checkDigits=generateCheckDigits(iban);
				iban="DE"+checkDigits+iban.substring(4);
				if(validateIban(iban))
					flag=false;
			}
		}
		else if(countryCode=="NL"){
			while(flag){
				iban="NL"+"00"+randomCode(4)+randomCode(8)+randomCode(2);
				checkDigits=generateCheckDigits(iban);
				iban="NL"+checkDigits+iban.substring(4);
				if(validateIban(iban))
					flag=false;
			}
		}
		else if(countryCode=="AT"){
			while(flag){
				iban="AT"+"00"+randomCode(5)+randomCode(8)+randomCode(3);
				checkDigits=generateCheckDigits(iban);
				iban="AT"+checkDigits+iban.substring(4);
				if(validateIban(iban))
					flag=false;
			}
		}
		return iban;
	}
	public String randomCode(int length){
		Integer result=0;
		
		while(result.toString().length()<length)
		{
			result=new Integer(new Random().nextInt(1000000000));
		}
		
		return result.toString().substring(0,length);
	}
	public String displayIban(String iban)
	{
		int i=0,j=0;
		String res="";
		for(i=0;i<iban.length();){
			for(j=0;j<=3 && i<iban.length();j++){
				res=res+iban.charAt(i);
				i++;
			}
			res+=" ";
		}
		return res;
	}

}
