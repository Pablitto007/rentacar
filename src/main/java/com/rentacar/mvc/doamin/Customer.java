package com.rentacar.mvc.doamin;

import java.util.Objects;


/**
 * Class Customer has two subtypes (Person and Company) implemented in
 * database. Depending on Customer's type some fields have to be null and
 * some other not null. Field customerType identifies Customer's type.
 * Database implements appropriate declarative constraints to check these
 * assumptions.
 */

public class Customer {

	private Integer customerId;
	private String streetAddress;
	private String city;
	private String postalCode;
	private String companyName;
	private String taxId;
	private String personName;
	private String personSurname;
	private String idCard;
	private String customerType;
	private String email;
	private String password;
	
	
	public Customer(){	
	}

	public Customer(Integer customerId, String streetAddress, String city, String postalCode, String companyName,
			String taxId, String personName, String personSurname, String idCard, String customerType, String email,
			String password) {

		this.customerId = customerId;
		this.streetAddress = streetAddress;
		this.city = city;
		this.postalCode = postalCode;
		this.companyName = companyName;
		this.taxId = taxId;
		this.personName = personName;
		this.personSurname = personSurname;
		this.idCard = idCard;
		this.customerType = customerType;
		this.email = email;
		this.password = password;
	}


	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonSurname() {
		return personSurname;
	}

	public void setPersonSurname(String personSurname) {
		this.personSurname = personSurname;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		if (customerType.equalsIgnoreCase("PERS")) {
			return Objects.hash(customerId, idCard, email);
		} else {
			return Objects.hash(customerId, taxId, email);
		}
	}
	
	/**
	 * customerId, taxId and idCard are unique identifiers so it is sufficient to compare these fields only
	 * 
	 */

	@Override
	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;
		if (otherObject == null || !(otherObject instanceof Customer))
			return false;

		Customer customer = (Customer) otherObject;
		if (customerType.equalsIgnoreCase("PERS")) {
			return this.customerId == customer.customerId && Objects.equals(this.idCard, customer.idCard)
					&& Objects.equals(this.email, customer.email);
		} else {
			return this.customerId == customer.customerId && Objects.equals(this.taxId, customer.taxId)
					&& Objects.equals(this.email, customer.email);
		}
	}
	@Override 
	public String toString(){
		if (customerType.equalsIgnoreCase("PERS")) {
		return "Customer [Name: " + personName + " ,Surname: " + personSurname + 
				" ,Street Address: " + streetAddress + 
				", City: " + city + " ,Postal Code: " + postalCode +
				", ID Card number: " + idCard +  " ,Email: "+  email  + "]";
		}else{
			return "Customer [Company Name: " + companyName + 
					" ,Street Address: " + streetAddress + 
					", City: " + city + " ,Postal Code: " + postalCode +
					", Tax ID number: " + taxId + " ,Email: "+  email + "]";
		}
	}
}