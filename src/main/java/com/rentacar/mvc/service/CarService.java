package com.rentacar.mvc.service;

import java.util.List;
import java.util.Set;

import com.rentacar.mvc.doamin.Car;

public interface CarService {
	
	Set<Car> getAvailableCars();
	List<Car> getAllCars();
	Car getCarById(int carID);
	void addCar(Car car);
}
