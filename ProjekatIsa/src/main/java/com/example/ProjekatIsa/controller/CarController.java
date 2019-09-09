package com.example.ProjekatIsa.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.Discount;
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.RatingCar;
import com.example.ProjekatIsa.model.RatingRoom;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.repository.DiscountRepository;
import com.example.ProjekatIsa.repository.FilijaleRepository;
import com.example.ProjekatIsa.repository.RatingCarRepository;
import com.example.ProjekatIsa.repository.RentalCarRepository;
import com.example.ProjekatIsa.service.CarService;
import com.example.ProjekatIsa.service.FilijaleService;
import com.example.ProjekatIsa.service.RentalCarService;
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
	FilijaleRepository filRepository;
	
	@Autowired
	RentalCarService rentcarService;
	
	@Autowired
	RentalCarRepository rentRepository;
	
	@Autowired 
	DiscountRepository discountRepository;
	
	@Autowired
	RatingCarRepository ratingCarRepository;
	
	
	
	@RequestMapping(
			value = "/getDiscountCars/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Discount>>  getDiscountCars(@PathVariable("id") Long id) {
		
		RentACar rent = rentRepository.findOneById(id);
		List<Discount> pomocni = discountRepository.findAllByRentACar(rent);
		
		System.out.println("Pronasao je discount vozila" + pomocni);
		return new ResponseEntity<List<Discount>>(pomocni, HttpStatus.OK);
	}
	
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
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAll(@RequestBody Long Id) {
		
		Car car = new Car();
		RentACar servis = rentRepository.findOneById(Id);
		
		List<Car> returnList = new ArrayList<Car>();
		returnList = carRepository.findAllByRentalcars(servis);
		System.out.println("Pronasao" + returnList);
		if (returnList==null) {
			return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Car>>(returnList, HttpStatus.OK);
		}	
		
}
	
	
	
	@RequestMapping(
			value = "/getCarById", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCarById(@RequestBody Long Id) {
		System.out.println("Usaoooo");
		Car car = new Car();
		Discount discount = discountRepository.findOneById(Id);
		
		List<Car> returnList = new ArrayList<Car>();
		returnList = carRepository.findAllByDiscount(discount);
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
	
	
	@RequestMapping(value="/searchCar/{id1}/{id2}/{id3}/{id4}/{id5}/{id6}",method=RequestMethod.GET)
	ResponseEntity<List<CarDTO>> searchCar(@PathVariable("id1") String pocDatum,@PathVariable("id2") String endDatum,
			@PathVariable("id3") Long id,@PathVariable("id4") String category,@PathVariable("id5") int cenaOd,@PathVariable("id6") int cenaDo){
		
		   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   Date startDate = null;
		   Date endDate = null;
		   
		   try {
			   startDate = dateFormat.parse(pocDatum);
			   endDate = dateFormat.parse(endDatum);
		   } catch (ParseException e) {
			   e.printStackTrace();
			   
		   }
  
		
		   RentACar servis = rentcarService.findOneById(id);
		   List<Filijale> filServisa = filRepository.findAllByRentalcars(servis);
		   List<Car> carsfromService = new ArrayList<>();
		   List<CarDTO> carsfromServiceDTO = new ArrayList<>();
		   
		   for(Filijale fil : filServisa) {
			   
			   List<Car> carsfromFil = new ArrayList<Car>();
			   carsfromFil = fil.getCars();
			   
			   for(Car cars : carsfromFil) {
				   
				   List<CarReservation> carReservation = cars.getReservation();
				   boolean free = true;
				   
				   int reserved = 0;
				   for(CarReservation res : carReservation) {
					   
					   free = checkforfree(res, startDate, endDate);
					   
					   if(!free)
					   {
						   reserved++;
					   }
					   
				   }
				   
				   if(reserved==0)
				   {
					   //cena nije oznacena u pretrazi
					   if(cenaOd == -1 && cenaDo==-1)
					   {
						   if(cars.getCategory().getMark().equals(category))
						   {
							   carsfromService.add(cars);	
						   }
					   }
					   else if(cenaOd==-1)
					   {
						   if(cars.getCategory().getMark().equals(category) && cars.getPrice() <= cenaDo)
						   {
							   carsfromService.add(cars);	   
						   }
						   
					   }
					   else if(cenaDo==-1)
					   {
						   if(cars.getCategory().getMark().equals(category) && cars.getPrice() >= cenaOd)
						   {
							   carsfromService.add(cars);	   
						   }
					   }
					   else {
						   if(cars.getCategory().getMark().equals(category) && cars.getPrice() >= cenaOd && cars.getPrice() <= cenaDo)
						   {
							   carsfromService.add(cars);	   
						   }
						   
					   }
					   //Car dto = new Car();
					   //carsfromService.add(cars);
				   }
				   
				   
			   }
		   }
		   
		   for(Car cars : carsfromService) {
			   System.out.println("Vozilo" + cars.getName() + "" + cars.getReservation().size());
			   carsfromServiceDTO.add(new CarDTO(cars));
		   }
		   
		   
		    System.out.println("pronadjena vozila" + carsfromService.size());		
		    return new ResponseEntity<>(carsfromServiceDTO,HttpStatus.OK);
	}
	
	
	public boolean checkforfree(CarReservation res, Date returnDate, Date pickupDate) {
		
		//provjeravamo datum koji smo unijeli za preuzimanje vozila
		//ako je on nakon datuma vracanja vozila koji je registrovan -ok
		if(pickupDate.getTime() >= res.getEndDate().getTime()) {
			return true;
			
		}
		else {
			if(returnDate.getTime() <= res.getStartDate().getTime())
			{
				return true;
			}
			else {
				return false;
			}
		}
		
	}
	
	@RequestMapping(value="/getRatingCar/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RatingCar>>  getRatingCar(@PathVariable("id") Long idCar) {

		List<RatingCar> returnList = new ArrayList<RatingCar>();
		Car car = carService.findOneById(idCar);
		returnList = ratingCarRepository.findAllByCar(car);
		
		return new ResponseEntity<List<RatingCar>>(returnList,HttpStatus.OK);

	}
	
	
	
	

}
