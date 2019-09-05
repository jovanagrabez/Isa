package com.example.ProjekatIsa.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.CarReservationDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.repository.CarReservationRepository;
import com.example.ProjekatIsa.repository.UserRepository;
import com.example.ProjekatIsa.service.CarService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carReservation")
public class CarReservationController {
	
	@Autowired
	CarService carService;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	CarReservationRepository carresRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/reserveCar",method = RequestMethod.POST)
	ResponseEntity<CarReservationDTO> reserve(@RequestBody CarReservationDTO carRes){
		
		System.out.println("Usao u kontroler rezervacije");
		CarReservation res = new CarReservation();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate = null;
		Date endDate = null;
	
			
			startDate = carRes.getStartDate();
			endDate = carRes.getEndDate();
			
			res.setStartDate(startDate);
			res.setEndDate(endDate);
			
			Car car = new Car();
		    car = carService.findOneById(carRes.getCar().getId());
			System.out.println(car.getName() + "Id vozila");
			User user = userRepository.findOneById(carRes.getUser().getId());
			System.out.println(user.getFirstName() + "Korisnik");
			
			if(!reserved(car,startDate,endDate)) {
				
				res.setPickupPlace(carRes.getPickupPlace());
				res.setReturnPlace(carRes.getReturnPlace());
				res.setNumPeople(carRes.getNumPeople());
				res.setNumDays(carRes.getNumDays());
				res.setCategory(carRes.getCategory());
				////res.setDayRez(carRes.getDayRez());
				//res.setTotalPrice(carRes.getTotalPrice());
				res.setUser(user);
				res.setCar(car);
				
				carresRepository.save(res);
				
				return new ResponseEntity<>(new CarReservationDTO(res),HttpStatus.CREATED);
				
				
			}else
			{
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		
	}
	
	
	public boolean reserved(Car c, Date pickupDate, Date returnDate) {
		
		//System.out.println("PRONADJI F AUTO" + carReservationService.allCarReservation(c.getId()));
		
		List<CarReservation> resCar = carresRepository.findAllByCar(c);
		
		for(CarReservation reservation : resCar) {
			
			if(returnDate.getTime() >= reservation.getStartDate().getTime() && returnDate.getTime()<= reservation.getEndDate().getTime())
			{
				return true;
			} else if(pickupDate.getTime() >= reservation.getStartDate().getTime() && pickupDate.getTime() <= reservation.getEndDate().getTime())
			{
				return true;
			}
		}
		return false;
	}

}
