package com.kafein.model;

public class CarsDTO {
	private Long id;
	private String carBrand;
	private String carModel;
	
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarModel() {
		return carModel;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	
}
