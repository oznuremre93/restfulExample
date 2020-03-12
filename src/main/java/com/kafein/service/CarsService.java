package com.kafein.service;

import java.util.List;

import com.kafein.exception.NoCarFoundException;
import com.kafein.model.Cars;
import com.kafein.model.CarsDTO;

public interface CarsService{

//	 public UUID insertCars(CarsDTO carsDTO);

	 public String insertCars(Cars cars);
	 public Boolean checkModel(String model);
	 public List<Cars> getAllCars();
	 public Cars updateCar(CarsDTO car) throws NoCarFoundException;
	 public void deleteCars(Long id);
	 
}
