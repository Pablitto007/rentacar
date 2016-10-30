package com.rentacar.mvc.doamin;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;


/**
 * Class Customer has two subtypes (Person and Company) implemented in
 * database. Depending on Customer's type some fields have to be null and
 * some other not null. Field customerType identifies Customer's type.
 * Database implements appropriate declarative constraints to check these
 * assumptions.
 */

public class Customer {

	private Integer customerId;
	

	@Size(min=1, max=40, message = "{Size.validation}") 
	private String streetAddress;
	
	@Size(min=1, max=25, message = "{Size.validation}") 
	private String city;
	
	//postal code has to be in 00-000 format
	@Pattern(regexp = "\\d{2}-\\d{3}", message ="{PostalCode.validation}")
	private String postalCode;
	
	private String companyName;
	private String taxId;
	private String personName;
	private String personSurname;
	private String idCard;
	private String customerType;
	
	@Email(message = "{Email.validation}")
	private String email;
	
	//password has to contain at least one digit and must not contain any of !@#*^... char
	@Pattern(regexp = "\\w*[\\d]+\\w*", message ="{Password.validation}")
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
	
	/**
	 *Email is unique in database
	 * 
	 */

	@Override
	public int hashCode() {
		
		int hash = 31;
		hash += 109 + (email != null ? email.toLowerCase().trim().hashCode() : 0);
		hash += 109 + (streetAddress != null ? streetAddress.toLowerCase().trim().hashCode() : 0);
		hash += 109 + (city != null ? city.toLowerCase().trim().hashCode() : 0);
		return hash;
	}
	
	@Override
	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;
		if (otherObject == null || !(otherObject instanceof Customer))
			return false;

		Customer customer = (Customer) otherObject;
		return ((this.email != null && customer.email != null) ? this.email.toLowerCase().trim().equals(customer.email.toLowerCase().trim()) :false)
				&& ((this.streetAddress != null && customer.streetAddress != null) ? this.streetAddress.toLowerCase().trim().equals(customer.streetAddress.toLowerCase().trim()) :false)
				&& ((this.city != null && customer.city != null) ? this.city.toLowerCase().trim().equals(customer.city.toLowerCase().trim()) :false);
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
