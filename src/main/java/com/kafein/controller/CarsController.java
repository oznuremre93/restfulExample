//package com.kafein.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.SystemPropertyUtils;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.kafein.constants.Constants;
//import com.kafein.exception.NoCarFoundException;
//import com.kafein.model.Cars;
//import com.kafein.model.CarsDTO;
//import com.kafein.repository.CarsRepository;
//import com.kafein.service.CarsService;
//
//@Controller
//@RequestMapping(value = "/secured")
//public class CarsController {
//	@Autowired
//	private CarsService carsServices;
//	
//	@Autowired
//	private CarsRepository carsRepository;
//	
//	@GetMapping(value = "/cars")
//	@ResponseBody
//	private List<Cars> carList() {
//		List<Cars> carsList = carsServices.getAllCars();
//	    System.out.println("carsList :" +carsList.toString());
//		return carsList;
//	}
//	
//	@PutMapping(value = "/addCars", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	private ResponseEntity<?> addCars(@RequestBody Cars cars) {
//		if (cars == null) {
//			return ResponseEntity.ok().body(Constants.getCarsNotNull());
//		} 
//		if(carsServices.checkModel(cars.getCarModel())) {
//			return ResponseEntity.ok().body(Constants.getAlreadyaddedModel());
//		}else
//		{
//			carsServices.insertCars(cars);
//			return ResponseEntity.ok().body(Constants.getSuccessMessage());
//		}
//	}
//	
//	@DeleteMapping(value = "/deleteCars/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	 public ResponseEntity<?> deletePost(@PathVariable Long id) {
//		 carsServices.deleteCars(id);
//		return ResponseEntity.ok().body(Constants.getSuccessMessage());
//	}
//
//	@RequestMapping(value = "/updateCars", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	private ResponseEntity<?> updateCars(@RequestBody CarsDTO cars) throws NoCarFoundException {
//		if (cars == null) {
//			return ResponseEntity.ok().body(Constants.getCarsNotNull());
//		}
//			carsServices.updateCar(cars);
//		return ResponseEntity.ok().body(Constants.getSuccessMessage());
//	}
//}