package com.example.ProjekatIsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.service.RentalCarService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rentalcars")
public class RentalCarController {
	@Autowired
	private RentalCarService rentalcarService;
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RentACar>  getRentalCar() {
		
		System.out.println("Number of hotels: " + rentalcarService.getAll().size());
		
		return rentalcarService.getAll();
}

}
