package com.rentacar.test.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.rentacar.mvc.doamin.Customer;

public class CustomerUnitTest {

	private Customer CUSTOMER;
	private Customer EQUAL_ONE;
	private Customer NOT_EQUAL_ONE;
	private Customer NULL_FIELDS;

	@Before
	public void before() {
		CUSTOMER = new Customer(5, "Some Street 2", "Warsaw", "09-222", null, null, "James", "Bond", "AUH12345", "PERS",
				"jamesb@bond.com", "passMe");
		EQUAL_ONE = new Customer(5, "Some Street 2", "Warsaw", "09-222", null, null, "James", "Bond", "AUH12345",
				"PERS", "jamesb@bond.com", "passMe");
		NOT_EQUAL_ONE = new Customer(10, "Another Location", "NY", "44-000", null, null, "James", "Another", "ABB1234",
				"PERS", "some@another.com", "passMe");
		NULL_FIELDS = new Customer();
	}

	@Test
	public void equalsTest() {
		assertTrue(CUSTOMER.equals(EQUAL_ONE));
		assertFalse(CUSTOMER.equals(NOT_EQUAL_ONE));
		assertFalse(CUSTOMER.equals(NULL_FIELDS));
	}
	
	@Test
	public void hashTest() {
		assertTrue(CUSTOMER.hashCode() == EQUAL_ONE.hashCode());
		assertFalse(CUSTOMER.hashCode() == NOT_EQUAL_ONE.hashCode());
		assertFalse(CUSTOMER.hashCode() == NULL_FIELDS.hashCode());
		//all fields are null
		assertTrue(NULL_FIELDS.hashCode() == 358);
	}
	
	@Test
	public void regexAnnotationTest(){
		
		//postal code has to be in 00-000 format
		String postalRegexAnnotation = "\\d{2}-\\d{3}";
		
		assertTrue(("02-220").matches(postalRegexAnnotation));
		assertFalse(("AA-220").matches(postalRegexAnnotation));
		
		//password has to contain at least one digit and must not contain any of !@#*^... char
		String passwordRegexAnnotation = "\\w*[\\d]+\\w*";
		
		assertTrue(("pas8s").matches(passwordRegexAnnotation)); 
		assertTrue(("PA1ss123").matches(passwordRegexAnnotation)); 
		assertFalse(("p#@as9").matches(passwordRegexAnnotation));
		assertFalse(("noDigit").matches(passwordRegexAnnotation));		
	}
}
