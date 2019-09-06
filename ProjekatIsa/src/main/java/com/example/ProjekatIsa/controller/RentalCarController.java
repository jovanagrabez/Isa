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

import com.example.ProjekatIsa.DTO.FilijaleDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.repository.FilijaleRepository;
import com.example.ProjekatIsa.repository.RentalCarRepository;
import com.example.ProjekatIsa.service.CarService;
import com.example.ProjekatIsa.service.RentalCarService;

@RestController
@RequestMapping(value = "/rentalcars",produces = MediaType.APPLICATION_JSON_VALUE)
public class RentalCarController {
	
	
	@Autowired
	private RentalCarService rentalcarService;
	
	@Autowired 
	private RentalCarRepository rentalcarRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private FilijaleRepository filRepository;
	
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
	
	@RequestMapping(
			value = "/getFilijale", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getFilijale(@RequestBody Long Id) {
		
		RentACar rentservice = rentalcarService.findOneById(Id);
		
		List<Filijale> returnList = new ArrayList<Filijale>();
		returnList = filRepository.findAllByRentalcars(rentservice);
		if (returnList==null) {
			return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Filijale>>(returnList, HttpStatus.OK);
		}
		
		
}
	
	@RequestMapping(value="/changeService/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> changeService(@RequestBody RentACar newService,@PathVariable("id") Long id){
		
		System.out.println("Usao u change servis");
		
		RentACar oldService = rentalcarService.findOneById(id);
		
		if(oldService!=null) {
			if(newService.getName()!=null) {
				oldService.setName(newService.getName());
			}
			if(newService.getAdress()!=null) {
				oldService.setAdress(newService.getAdress());
			}
			if(newService.getDescription()!=null) {
				oldService.setDescription(newService.getDescription());
			}
			rentalcarRepository.save(oldService);
			
			return new ResponseEntity<RentACar>(oldService,HttpStatus.OK);			
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		}		
		
	}
	
	@RequestMapping(value="/deleteService",
			method = RequestMethod.POST)
	public ResponseEntity<?> deleteService(@RequestBody Long id) {
		System.out.println("Usao u delete service");
		
		RentACar r = rentalcarRepository.findOneById(id);
		rentalcarRepository.delete(r);
		return new ResponseEntity<>(HttpStatus.OK);

			
		}
	
	@RequestMapping(value="/addFil/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> addFil(@RequestBody FilijaleDTO newFil, @PathVariable("id") Long id){
		System.out.println("Usao u add filijale");
		
		RentACar r = rentalcarRepository.findOneById(id);
		Filijale f = new Filijale(newFil);
		
		//dodavanje u model
		r.addFilijale(f);
		f.setRentACar(r);
		
		this.filRepository.save(f);
		rentalcarRepository.save(r);
		return new ResponseEntity<>(null, HttpStatus.OK);

		
	}
	}


