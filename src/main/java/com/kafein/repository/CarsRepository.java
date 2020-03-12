package com.kafein.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kafein.model.Cars;

public interface CarsRepository extends JpaRepository<Cars, Long> {
    
	@Modifying
	@Query("select carModel from Cars where carModel = :model ")
	public Boolean checkModel(String model);
	
}
