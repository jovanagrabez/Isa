package com.example.ProjekatIsa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.service.CarService;
import com.example.ProjekatIsa.service.RentalCarService;

@RestController
@RequestMapping(value = "/rentalcars",produces = MediaType.APPLICATION_JSON_VALUE)
public class RentalCarController {
	
	
	@Autowired
	private RentalCarService rentalcarService;
	
	@Autowired
	private CarRepository carRepository;
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RentACar>  getRentalCar() {
		
		System.out.println("Number of rent a car services: " + rentalcarService.getAll().size());
		
		return rentalcarService.getAll();
}
	
	
	@RequestMapping(
			value = "/getCars", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCars(@RequestBody Long Id) {
		
		RentACar rentservice = rentalcarService.findOneById(Id);
		
		List<Car> returnList = new ArrayList<Car>();
		returnList = carRepository.findAllByRentalcars(rentservice);
		if (returnList==null) {
			return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Car>>(returnList, HttpStatus.OK);
		}
		
		
		
}

}
