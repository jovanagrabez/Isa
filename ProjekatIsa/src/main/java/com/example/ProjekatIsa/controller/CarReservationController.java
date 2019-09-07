package com.example.ProjekatIsa.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.CarReservationDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.repository.CarReservationRepository;
import com.example.ProjekatIsa.repository.ReservationRoomRepository;
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
	
	@Autowired
	ReservationRoomRepository roomRepository;
	
	
	@RequestMapping(value="/getUserRes/{id}",method = RequestMethod.POST)
	ResponseEntity<List<CarReservationDTO>> getUserRes(@PathVariable("id") Long id){
		System.out.println("USAAAO");
		
		User user = userRepository.findOneById(id);
		List<CarReservation> lista=carresRepository.findAllByUser(user);
		System.out.println(lista + "return lista rezervacija");
		
		List<CarReservationDTO> rezDTO = new ArrayList<>();
		
		for(CarReservation cr : lista) {
			
			if(cr.isFlag()) {
				rezDTO.add(new CarReservationDTO(cr));

			}
			
		}
		
		return new ResponseEntity<>(rezDTO,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="delete/{id}", method = RequestMethod.DELETE)
	ResponseEntity<?> obrisiVozilo(@PathVariable("id")Long id){
		
		CarReservation rs = carresRepository.getOne(id);
		System.out.println(rs.getCar());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();    
		String danasString = dateFormat.format(today);
		
		Date danasDatum=null;
		try {
			danasDatum = dateFormat.parse(danasString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("datum preuzimanje" + rs.getStartDate());
		System.out.println("Danaas " + danasDatum);
		
		Long razlika = getDateDiff(danasDatum,rs.getStartDate(),TimeUnit.DAYS);

		
	    System.out.println("Broj dana: " + razlika);
		
		if(razlika < 2)
			{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}else
			{
		    carresRepository.delete(rs);
			return new ResponseEntity<>(HttpStatus.OK);		
			}
		
		
		
	}
	
	
	@RequestMapping(value="deleteHotelR/{id}", method = RequestMethod.DELETE)
	ResponseEntity<?> obrisihotel(@PathVariable("id")Long id){
		
		ReservationRoom rs = roomRepository.getOne(id);
		System.out.println(rs.getRoom());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();    
		String danasString = dateFormat.format(today);
		
		Date danasDatum=null;
		try {
			danasDatum = dateFormat.parse(danasString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("datum preuzimanje" + rs.getStartDate());
		System.out.println("Danaas " + danasDatum);
		
		Long razlika = getDateDiff(danasDatum,rs.getStartDate(),TimeUnit.DAYS);

		
	    System.out.println("Broj dana: " + razlika);
		
		if(razlika < 2)
			{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}else
			{
		    roomRepository.delete(rs);
			return new ResponseEntity<>(HttpStatus.OK);		
			}
		
		
		
	}
	
	
	@RequestMapping(value="/getUserHotelRes/{id}",method = RequestMethod.POST)
	ResponseEntity<List<ReservationRoom>> getUserHotelRes(@PathVariable("id") Long id){
		System.out.println("USAAAO");
		
		User user = userRepository.findOneById(id);
		List<ReservationRoom> lista=roomRepository.findAllByUser(user);
		System.out.println(lista + "return lista rezervacija");
		
		return new ResponseEntity<>(lista,HttpStatus.OK);
		
	}
	
	
	
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
	
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	

}
