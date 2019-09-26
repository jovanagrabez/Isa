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
import com.example.ProjekatIsa.DTO.PricingDTO;
import com.example.ProjekatIsa.model.AdditionalServiceForHotel;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Pricing;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.service.PricingService;
import com.example.ProjekatIsa.service.RoomService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pricing")
public class PricingController {
	
	@Autowired
	private PricingService pricingService;
	
	@Autowired
	private RoomService roomService;
	
	@RequestMapping(
			value = "/getAllPricing/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllPricing(@PathVariable("id") Long id) {
		List<Pricing> returnList = new ArrayList<Pricing> ();
		Room room = roomService.findOneById(id);
		
		returnList = pricingService.findAllByRoom(room);
		if (!returnList.isEmpty()) {
	        return new ResponseEntity<List<Pricing>>(returnList,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(
			value="/addPricing/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> addService(@RequestBody PricingDTO newPricing,
										@PathVariable("id") Long id){
		System.out.println("Dosao u add pricing hotel");
		
		Room room = roomService.findOneById(id);
		Pricing newP = new Pricing(newPricing);
		newP.setRoom(room);
		List<Pricing> pomList = pricingService.findAllByRoom(room);
		if (!pomList.isEmpty()) {
			for (Pricing p : pomList) {
				System.out.println("pretrazujem jedna pricing");
				System.out.println("sgsdg "+p.getDateTo().getTime());
				System.out.println("sgsdg "+ newPricing.getDateTo().getTime());
				//provjeravamo da se ne poklapa sa drugim datumom
				//ako je pocetak novog veci od nekog kraja 
				if (newPricing.getDateFrom().getTime()<p.getDateTo().getTime() || 
						newPricing.getDateTo().getTime()<p.getDateTo().getTime()) {
					System.out.println("pronasao");
					return new ResponseEntity<>(null, HttpStatus.OK);
				}
			}
		}
		pricingService.save(newP);
		return new ResponseEntity<Pricing>(newP, HttpStatus.OK);
		
	}
	@RequestMapping(
			value="/deletePricing/{id}",
			method = RequestMethod.GET)
		public ResponseEntity<?> deleteService(@PathVariable("id") Long id){
			System.out.println("Dosao u delete pricing");
			
			//Hotel h = hotelRepository.findOneById(id);
			Pricing p = pricingService.findOneById(id);
			try {
				pricingService.deletePricing(p);
				return new ResponseEntity<>(null, HttpStatus.OK);
			}catch(Exception e ) {
				
			}
			
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
		}
	
	@RequestMapping(value="/changePricing/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> changePricing(@RequestBody PricingDTO newPricing,
										@PathVariable("id") Long id){
		System.out.println("Dosao u change pricing");
		Pricing pp = new Pricing(newPricing);
		Pricing p = pricingService.findOneById(id);
		
		if (newPricing.getPrice()>0) {
			p.setPrice(pp.getPrice());
			System.out.println("setujem cijenyu");
		}		
		try {
			System.out.println("cuvam");
			pricingService.save(p);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	
	}
}
