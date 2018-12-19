package com.example.ProjekatIsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.repository.RatingHotelRepository;
import com.example.ProjekatIsa.service.HotelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RatingHotelRepository ratingHotelService;
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel>  getCinemas() {
		
		System.out.println("Number of cinemas: " + hotelService.getAll().size());
		
		return hotelService.getAll();
}}
