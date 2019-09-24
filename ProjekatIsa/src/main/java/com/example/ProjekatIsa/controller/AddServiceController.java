package com.example.ProjekatIsa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.AdditionalServiceForHotelDTO;
import com.example.ProjekatIsa.model.AdditionalServiceForHotel;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.repository.AdditionalServiceForHotelRepository;
import com.example.ProjekatIsa.repository.HotelRepository;
import com.example.ProjekatIsa.service.AdditionalServiceForHotelService;
import com.example.ProjekatIsa.service.HotelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/addServices")
public class AddServiceController {

	@Autowired
	private HotelService hotelService;
	
	@Autowired 
	private HotelRepository hotelRepository;
	
	@Autowired
	private AdditionalServiceForHotelRepository addRepository;
	
	@Autowired
	private AdditionalServiceForHotelService addService;
	
	@RequestMapping(
			value = "/getAllServices/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getServices(@PathVariable("id") Long id) {
		List<AdditionalServiceForHotel> returnList = new ArrayList<AdditionalServiceForHotel> ();
		Hotel h = hotelRepository.findOneById(id);
		returnList = addRepository.findAllByHotel(h);
		if (returnList!=null) {
	        return new ResponseEntity<List<AdditionalServiceForHotel>>(returnList,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(
			value="/addService/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> addService(@RequestBody AdditionalServiceForHotelDTO newService,
										@PathVariable("id") Long id){
		System.out.println("Dosao u add service hotel");
		
		Hotel hotel = hotelRepository.findOneById(id);
		AdditionalServiceForHotel a =new AdditionalServiceForHotel(newService);
		
		//dodavanje u model
		hotel.addAdditionalService(a);
		a.setHotel(hotel);
		
		//cuvanje u bazu
		this.addService.addService(a);
		hotelRepository.save(hotel);
			return new ResponseEntity<>(null, HttpStatus.OK);
		
	}
		
	
	@RequestMapping(
			value="/deleteService/{id}",
			method = RequestMethod.GET)
		public ResponseEntity<?> deleteService(@PathVariable("id") Long id){
			System.out.println("Dosao u delete service");
			
			//Hotel h = hotelRepository.findOneById(id);
			AdditionalServiceForHotel a = addRepository.findOneById(id);
			
			try {
				addService.deleteAdditionalServiceForHotel(a);
				return new ResponseEntity<>(null, HttpStatus.OK);
			}catch(Exception e ) {
				
			}
			
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
		}
		
	@RequestMapping(value="/changeService/{id}",
				method = RequestMethod.POST)
		public ResponseEntity<?> changeService(@RequestBody AdditionalServiceForHotelDTO add,
											@PathVariable("id") Long id){
			System.out.println("Dosao u change service");
			AdditionalServiceForHotel aa = new AdditionalServiceForHotel(add);
			AdditionalServiceForHotel a = addRepository.findOneById(id);
			System.out.println("id servisa :"  + id);
			
			
			if (add.getName()!=null) {
				a.setName(aa.getName());
			}
			if (add.getPrice()!=null) {
				a.setPrice(aa.getPrice());
			}
			
			try {
				addRepository.save(a);
				return new ResponseEntity<>(null, HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		
		}
	
}
