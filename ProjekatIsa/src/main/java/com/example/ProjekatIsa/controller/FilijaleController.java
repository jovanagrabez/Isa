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

import com.example.ProjekatIsa.DTO.CarDTO;
import com.example.ProjekatIsa.DTO.FilijaleDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.Category;
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.repository.CategoryRepository;
import com.example.ProjekatIsa.repository.FilijaleRepository;
import com.example.ProjekatIsa.repository.RentalCarRepository;
import com.example.ProjekatIsa.service.FilijaleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value= "/filijale",produces = MediaType.APPLICATION_JSON_VALUE)
public class FilijaleController {
	
	@Autowired
	FilijaleService filService;
	
	@Autowired
	FilijaleRepository filRepository;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired 
	RentalCarRepository rentRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@RequestMapping(value="/addFilijale",method = RequestMethod.POST)
	public ResponseEntity<?> addNewFil(@RequestBody Filijale fil){
		System.out.println("dosaooo u addFil");
		Filijale nova = new Filijale();
		nova.setAdresa(fil.getAdresa());
		nova.setDrzava(fil.getDrzava());
		nova.setGrad(fil.getGrad());
		
		filRepository.save(nova);
		return new ResponseEntity<>(nova,HttpStatus.OK);
		
	}
	
	@RequestMapping(
			value = "/getFilijales", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Filijale>  getFilijale() {
		System.out.println("Ukupno" + filService.getAll().size());	
		return filService.getAll();
}
	
	@RequestMapping(
			value = "/getCars/{id}", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCars(@PathVariable("id") Long id) {
		
		Filijale fil = filService.findOneById(id);
		
		List<Car> returnList = new ArrayList<Car>();
		returnList = carRepository.findAllByFilijale(fil);
		System.out.println("Pronasao" + returnList);
		if (returnList==null) {
			return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Car>>(returnList, HttpStatus.OK);
		}	
		
}
	
	@RequestMapping(value="changeFil/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> changeFil(@RequestBody Filijale newFil,@PathVariable("id") Long id){
		
		System.out.println("Usao u change filijale");
		
		Filijale fil = filRepository.findOneById(id);
		
		if(fil!=null) {
			if(newFil.getAdresa()!=null) {
				fil.setAdresa(newFil.getAdresa());
			}
			if(newFil.getDrzava()!=null) {
				fil.setDrzava(newFil.getDrzava());
			}
			if(newFil.getGrad()!=null) {
				fil.setGrad(newFil.getGrad());
			}
			
			filRepository.save(fil);
			
			return new ResponseEntity<Filijale>(fil,HttpStatus.OK);
		}
		
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		}
		
	}
	
	
	@RequestMapping(value="/deleteFil/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> deleteFil(@PathVariable("id") Long id){
		System.out.println("Usao u delete filijale");
		
		Filijale fil = filRepository.findOneById(id);
		filRepository.delete(fil);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/addCar/{id}/{idKategorije}",
			method = RequestMethod.POST)
	public ResponseEntity<?> addCar(@RequestBody CarDTO newCar, @PathVariable("id") Long id,@PathVariable("idKategorije") Long idKategorije){
		System.out.println("Usao u add filijale");
		
		Filijale fil = filRepository.findOneById(id);
		Car car = new Car(newCar);		
		//dodavanje u model
		fil.addCar(car);
		car.setFilijale(fil);		
		Category cat = new Category();
		cat = categoryRepository.findOneById(idKategorije);
		System.out.println("cat+++++++++++++" + cat.getId());
		car.setCategory(cat);
		
		
		this.carRepository.save(car);
		filRepository.save(fil);
		return new ResponseEntity<>(null, HttpStatus.OK);

		
	}
	
	

}
