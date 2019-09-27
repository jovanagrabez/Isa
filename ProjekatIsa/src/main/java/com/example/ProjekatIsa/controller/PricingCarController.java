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

import com.example.ProjekatIsa.DTO.PricingCarDTO;
import com.example.ProjekatIsa.DTO.PricingDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.Pricing;
import com.example.ProjekatIsa.model.PricingCar;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.service.CarService;
import com.example.ProjekatIsa.service.PricingCarService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pricingcar")
public class PricingCarController {
	
	@Autowired
	private PricingCarService pricingService;
	
	@Autowired
	private CarService carService;
	
	
	@RequestMapping(
			value = "/getAllPricing/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllPricing(@PathVariable("id") Long id) {
		List<PricingCar> returnList = new ArrayList<PricingCar> ();
		System.out.println("Usaaaaaaaaaaaao");
		Car car = carService.findOneById(id);
		
		returnList = pricingService.findAllByCar(car);
		if (!returnList.isEmpty()) {
	        return new ResponseEntity<List<PricingCar>>(returnList,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@RequestMapping(
			value="/addPricing/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> addService(@RequestBody PricingCarDTO newPricing,
										@PathVariable("id") Long id){
		System.out.println("Dosao u add pricing");
		
		Car car = carService.findOneById(id);
		PricingCar newP = new PricingCar(newPricing);
		newP.setCar(car);
		List<PricingCar> pomList = pricingService.findAllByCar(car);
		if (!pomList.isEmpty()) {
			for (PricingCar p : pomList) {
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
		return new ResponseEntity<PricingCar>(newP, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(
			value="/deletePricing/{id}",
			method = RequestMethod.GET)
		public ResponseEntity<?> deleteService(@PathVariable("id") Long id){
			System.out.println("Dosao u delete pricing");
			
			PricingCar p = pricingService.findOneById(id);
			try {
				pricingService.deletePricing(p);
				return new ResponseEntity<>(null, HttpStatus.OK);
			}catch(Exception e ) {
				
			}
			
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
		}
	
	
	@RequestMapping(value="/changePricing/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> changePricing(@RequestBody PricingCarDTO newPricing,
										@PathVariable("id") Long id){
		System.out.println("Dosao u change pricing");
		PricingCar pp = new PricingCar(newPricing);
		PricingCar p = pricingService.findOneById(id);
		
		if (newPricing.getPrice()>0) {
			p.setPrice(pp.getPrice());
			System.out.println("setujem cijenyu");
		}		
		try {
			System.out.println("cuvam");
			pricingService.save(p);
			return new ResponseEntity<>(pp, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	
	}
	

}
