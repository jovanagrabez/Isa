package com.example.ProjekatIsa.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.repository.RentalCarRepository;
import com.example.ProjekatIsa.service.CarService;
import com.example.ProjekatIsa.service.FilijaleService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	CarService carService;
	
	@Autowired
	FilijaleService filService;
	
	@Autowired
	RentalCarRepository rentRepository;
	
	@RequestMapping(value="/addCar",method = RequestMethod.POST)
	public ResponseEntity<?> addNewCar(@RequestBody CarDTO car){
		System.out.println("dosaooo u addCar");
		Car c = this.carService.addCar(new Car(car));
		return new ResponseEntity<Car>(c,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/deleteCar",
			method = RequestMethod.POST)
	public ResponseEntity<?> deleteCar(@RequestBody Long id) {
		System.out.println("Usao u delete service");
		
		Car car = carRepository.findOneById(id);
		carRepository.delete(car);
		return new ResponseEntity<>(HttpStatus.OK);

			
		}
	
	@RequestMapping(
			value = "/getCars", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCars(@RequestBody Long Id) {
		
		Filijale fil = filService.findOneById(Id);
		
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
	
	@RequestMapping(value="changeCar/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> changeFil(@RequestBody CarDTO newCar,@PathVariable("id") Long id){
		
		System.out.println("Usao u change car");
		
		Car old = carRepository.findOneById(id);
		Car car2 = new Car(newCar);
		
			if(newCar.getName()!=null) {
				old.setName(car2.getName());
			}
			if(newCar.getCar_number()!=null) {
				old.setCar_number(car2.getCar_number());
			}
			if(newCar.getPrice()!= 0) {
				old.setPrice(car2.getPrice());
			}
			
			try {
			
			carRepository.save(old);
			
			return new ResponseEntity<>(null,HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		
		
	}
	

}
