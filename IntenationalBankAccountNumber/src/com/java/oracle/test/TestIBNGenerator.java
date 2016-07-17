package com.java.oracle.test;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.java.oracle.generator.IBNGenerator;

public class TestIBNGenerator {
	IBNGenerator ibn=new IBNGenerator();
	static Set<String> ibanSet=new LinkedHashSet<String>();
	
@Test
public void testValidateIban() {

	
	Assert.assertEquals(ibn.validateIban("DE74 2808 7697 6604 0493 43"), true);
	Assert.assertEquals(ibn.validateIban("DE26 9026 9261 7242 1718 91"), true);
	Assert.assertEquals(ibn.validateIban("GB82 WEST 1234 5698 7654 32"), true);

}
@Test
public void testgenerateIBN() {

	int i=0;
	for(i=0;i<100;i++){
		ibanSet.add(ibn.generateIBN("DE"));
	}
	for(i=0;i<100;i++){
		ibanSet.add(ibn.generateIBN("NL"));
	}
	for(i=0;i<100;i++){
		ibanSet.add(ibn.generateIBN("AT"));
	}
	
	Assert.assertNotNull("No Duplicates Found", ibanSet);
}
@Test
public void testmod97() {

	Assert.assertEquals(ibn.mod97("3214282912345698765432161182"), 1);
	Assert.assertNotSame(ibn.mod97("321428291234569876543216"), 1);
	

}
@Test
public void testDisplayIban() {

	Iterator<String> it=ibanSet.iterator();
	String message=null;
	while(it.hasNext()){
		message=(String) it.next();
		Assert.assertNotSame("Message display is not changed ",message , ibn.displayIban(message));
	}
 }
	
}
