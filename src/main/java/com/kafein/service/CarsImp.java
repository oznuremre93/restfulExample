package com.kafein.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kafein.exception.NoCarFoundException;
import com.kafein.model.Cars;
import com.kafein.model.CarsDTO;
import com.kafein.repository.CarsRepository;


@Service
@Transactional
public class CarsImp implements CarsService {

	@Autowired
	private CarsRepository carsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public String insertCars(Cars cars) {
		carsRepository.save(cars);
		return null;
	}

	@Override
	public List<Cars> getAllCars() {
		return carsRepository.findAll();
	}
	
	@Override
	public Cars updateCar(CarsDTO carDto ) throws NoCarFoundException {
		Cars car = carsRepository.findById(carDto.getId()).orElseThrow(() -> new NoCarFoundException("No car found with id" + carDto.getId()));
		carsRepository.save(car);
		return car;
	}

	@Override
	public void deleteCars(Long id) {
		carsRepository.deleteById(id);
		
	}

	public Boolean checkModel(String model) {
		
		return carsRepository.checkModel(model);
	}
}
