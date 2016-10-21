package com.rentacar.mvc.repository;

import java.util.List;

import com.rentacar.mvc.doamin.Car;

public interface CarRepository {

	List<Car> getAvailableCars();
	
	List<Car> getAllCars();

	Car getCarById(int carID);
	
	void addCar(Car car);
	
	int count();

}
