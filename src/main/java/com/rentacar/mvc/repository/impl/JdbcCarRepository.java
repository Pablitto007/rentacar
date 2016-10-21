package com.rentacar.mvc.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rentacar.mvc.doamin.Car;
import com.rentacar.mvc.repository.CarRepository;

@Repository
public class JdbcCarRepository implements CarRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public  JdbcCarRepository (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/*
	 * Select only available cars - not reserved at the moment of using 
	 * application or not reserved ever.
	 */
	private static final String SELECT_SQL_AVAILABLE_CARS = 
			" SELECT * FROM CARS C LEFT JOIN TRANSACTIONS T ON C.CAR_ID = T.CAR_ID "
			+ "WHERE T.CAR_ID IN "
			+ "(SELECT CAR_ID from TRANSACTIONS WHERE TRUNC(CURRENT_DATE) > TRUNC(END_DATE)"
			+ " MINUS "
			+ "SELECT car_id FROM TRANSACTIONS WHERE "
			+ "TRUNC(CURRENT_DATE) BETWEEN TRUNC(START_DATE) AND TRUNC(END_DATE))"
			+ "OR T.TRANSACTION_ID IS NULL";
	
	private static final String SELECT_SQL_All_CARS = 
			" SELECT * FROM CARS ORDER BY CAR_ID";
	
	
	private static final String INSERT_SQL = "INSERT INTO Cars "
			+ "(Car_ID, VIN, Brand, Model, Year_of_Manufacture, "
			+ "Price_per_Day, Description) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	public int count(){
		return this.jdbcTemplate.queryForObject("SELECT COUNT(CAR_ID) FROM Cars", Integer.class);
	}
	
	@Override
	public List<Car> getAvailableCars() {
		return jdbcTemplate.query(SELECT_SQL_AVAILABLE_CARS, new CarMapper());
	}
	
	@Override
	public List<Car> getAllCars() {
		return jdbcTemplate.query(SELECT_SQL_All_CARS, new CarMapper());
	}

	@Override
	public Car getCarById(int carID) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM Cars WHERE CAR_ID = ?", new CarMapper(), carID);
	}
	
	@Override
	public void addCar(Car car) {
		
		this.jdbcTemplate.update(INSERT_SQL, new Object[]{
				car.getCarId(), car.getVin(), car.getBrand(), 
				car.getModel(), car.getYearOfManufacture(), 
				car.getPricePerDay(), car.getDescription()
		});
	}


	private static final class CarMapper implements RowMapper<Car> {

		@Override
		public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
			Car car = new Car();
			car.setCarId(rs.getInt("car_id"));
			car.setVin(rs.getString("vin").trim());
			car.setBrand(rs.getString("brand"));
			car.setModel(rs.getString("model"));
			car.setYearOfManufacture(rs.getInt("year_of_manufacture"));
			car.setPricePerDay(rs.getInt("price_per_day"));
			car.setDescription(rs.getString("description"));
			return car;
		}
	}
}
