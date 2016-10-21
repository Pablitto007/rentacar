package com.rentacar.mvc.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentacar.mvc.doamin.Car;
import com.rentacar.mvc.repository.CarRepository;
import com.rentacar.mvc.service.CarService;

@Service
@Transactional
public class CarServiceImpl implements CarService {

	private CarRepository carRepository;
	
	@Autowired
	public CarServiceImpl(CarRepository carRepository){
		this.carRepository = carRepository;
	}
	/*
	 * Eliminates duplicates
	 */
	@Override
	public Set<Car> getAvailableCars() {
		List<Car> carList = carRepository.getAvailableCars();
		Set<Car> carSet = new HashSet<>(carList);
		return carSet;
	}
	
	@Override
	public List<Car> getAllCars() {
		return carRepository.getAllCars();
	}

	@Override
	public Car getCarById(int carID) {
		return carRepository.getCarById(carID);
	}

	@Override
	public void addCar(Car car) {
		carRepository.addCar(car);

	}
}
