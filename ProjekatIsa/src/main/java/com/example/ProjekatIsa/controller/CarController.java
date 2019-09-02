package com.example.ProjekatIsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.CarDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.repository.RentalCarRepository;
import com.example.ProjekatIsa.service.CarService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	CarService carService;
	
	@Autowired
	RentalCarRepository rentRepository;
	
	@RequestMapping(value="/addCar",method = RequestMethod.POST)
	public ResponseEntity<?> addNewCar(@RequestBody CarDTO car){
		System.out.println("dosaooo u addCar");
		Car c = this.carService.addCar(new Car(car));
		return new ResponseEntity<Car>(c,HttpStatus.OK);
		
	}

}
