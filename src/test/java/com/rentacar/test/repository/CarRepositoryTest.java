package com.rentacar.test.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import javax.sql.DataSource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.rentacar.mvc.doamin.Car;
import com.rentacar.mvc.repository.CarRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=JdbcConfigTest.class)
public class CarRepositoryTest {
	
	@Autowired
	CarRepository carRepository;	
	
	@Autowired
	DataSource dataSource;
	
	@Test
	public void dataSourceNotNull(){
		assertNotNull(dataSource);
	}
	@Test
	public void carRepoNotNull(){
		assertNotNull(carRepository);
	}
	
	@Test
	@Transactional
	public void shouldAddCar(){
		int countCar = carRepository.count();
		carRepository.addCar(new Car(3, "WAUZXUX4FO8N34566", 
			"Audi", "A6", 2015, 520, "ABS, Central locking, Climatronic, ESP, On-board computer, Electric windows,"));
		assertEquals(countCar +1, carRepository.count());
	}
	
	@Test
	@Transactional
	public void shouldGetCarById(){
		assertCar(0, carRepository.getCarById(1));
		assertCar(1, carRepository.getCarById(2));
	}
	
	@Test
	@Transactional
	public void shouldGetAllCars(){
		List<Car> carList = carRepository.getAllCars();
		assertCar(0, carList.get(0));
		assertCar(1, carList.get(1));		
	}
	
	
	private static void assertCar(int expectedCarIndex, Car actual){
		Car expected = CARS[expectedCarIndex];
		assertEquals(expected, actual);
		
	}
	
	private static final Car[] CARS = new Car[2];
	
	@BeforeClass
	public static void before(){
	CARS[0] = new Car(1, "WAUZZZ4FO8N012345", 
			"Audi", "A8", 2012, 450, "Navigation system, ESP, On-board computer, Electric windows");
	CARS[1] = new Car (2, "WAUZXX4FO8N543210", 
			"Audi", "A4", 2011, 320, "Climatronic, ESP, On-board computer, Electric windows");
	}
}
