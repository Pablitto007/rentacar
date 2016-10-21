package com.rentacar.mvc.doamin;

import java.util.Objects;
import org.springframework.web.multipart.MultipartFile;

public class Car {

	private Integer carId;
	private String vin;
	private String brand;
	private String model;
	private int yearOfManufacture; 
	private int pricePerDay;
	private String description;
	private MultipartFile carImage;
	
	public Car(){

	}
	
	public Car(Integer carId, String vin, String brand, String model, int dateOfManufacture, int pricePerDay,
			String description) {
		this.carId = carId;
		this.vin = vin;
		this.brand = brand;
		this.model = model;
		this.yearOfManufacture = dateOfManufacture;
		this.pricePerDay = pricePerDay;
		this.description = description;
	}
	
	//getters and setters
	
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYearOfManufacture() {
		return yearOfManufacture;
	}
	public void setYearOfManufacture(int yearOfManufacture) {
		this.yearOfManufacture = yearOfManufacture;
	}
	public int getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MultipartFile getCarImage() {
		return carImage;
	}

	public void setCarImage(MultipartFile carImage) {
		this.carImage = carImage;
	}

	/**
	 * carId & vin are unique identifiers so it is sufficient to compare these two fields only
	 * 
	 */

	@Override
	public int hashCode(){
		return Objects.hash(carId, vin);
	}
	@Override
	public boolean equals(Object otherObject){
		if(this == otherObject)
			return true;
		if (otherObject == null || !(otherObject instanceof Car))
			return false;
		Car car = (Car)otherObject;
		return this.carId == car.carId && Objects.equals(this.vin, car.vin);
	}
	@Override 
	public String toString(){
		return "Car [id: " + carId +" ,VIN: " + vin + " ,Brand: " + brand + " ,Model: " + model + 
				" ,Year of manufacture: " + yearOfManufacture  + " ,Price per Day: " + pricePerDay +
				", Equipment: " + description + " ]";
	}
	
}
