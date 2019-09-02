package com.example.ProjekatIsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.HotelDTO;
import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.service.HotelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	//@Autowired
	//private RatingHotelRepository ratingHotelService;
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel>  getHotels() {
		
		System.out.println("Number of hotels: " + hotelService.getAll().size());
		
		return hotelService.getAll();
	}
	
	@PreAuthorize("hasAuthority('addHotel')")
	@RequestMapping(value="/addHotel",
			method = RequestMethod.POST)
	public ResponseEntity<?> addNewHotel(@RequestBody HotelDTO hotel){
		System.out.println("Dosao u add hotel");
		
		 Hotel h = this.hotelService.addHotel(new Hotel(hotel));
		
	        return new ResponseEntity<Hotel>(h,HttpStatus.OK);
		
	}
	
}
