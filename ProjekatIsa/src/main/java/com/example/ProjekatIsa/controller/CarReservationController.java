package com.example.ProjekatIsa.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
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
import com.example.ProjekatIsa.model.Discount;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.repository.CarReservationRepository;
import com.example.ProjekatIsa.repository.DiscountRepository;
import com.example.ProjekatIsa.repository.FlightRepository;
import com.example.ProjekatIsa.repository.FlightReservationRepository;
import com.example.ProjekatIsa.repository.ReservationRoomRepository;
import com.example.ProjekatIsa.repository.UserRepository;
import com.example.ProjekatIsa.service.CarReservationService;
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
	
	@Autowired
	FlightReservationRepository flightRepository;
	
	@Autowired
	FlightRepository fRepository;
	
	@Autowired
	DiscountRepository discountRepository;
	
	@Autowired
	CarReservationService carReservationService;
	
	
	@RequestMapping(value="/getUserRes/{id}",method = RequestMethod.POST)
	ResponseEntity<List<CarReservationDTO>> getUserRes(@PathVariable("id") Long id){
		System.out.println("USAAAO");
		
		User user = userRepository.findOneById(id);
		List<CarReservation> lista=carReservationService.findAllByUser(user);
		System.out.println(lista + "return lista rezervacija");
		System.out.println("KORISNIK" + id + user);
		
		List<CarReservationDTO> rezDTO = new ArrayList<>();
		
		for(CarReservation cr : lista) {
			
			rezDTO.add(new CarReservationDTO(cr));

			
			
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
	
	
	
	@RequestMapping(value="deleteFlight/{id}", method = RequestMethod.DELETE)
	ResponseEntity<?> obrisileet(@PathVariable("id")Long id){
		
		Flight f = fRepository.getOne(id);
		System.out.println(f.getTravel_time());
		
		FlightReservation fs = flightRepository.findByFlightId(id);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();    
		String danasString = dateFormat.format(today);
		DateTime danas = null;
		Date danasDatum=null;
		try {
			
			
			danasDatum = dateFormat.parse(danasString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("vrijeme poletanja" + f.getTake_off());
		System.out.println("Vrijeme " + danasDatum);
		
		Long razlika = getDateDiff(danasDatum,f.getTake_off(),TimeUnit.HOURS);

		
	    System.out.println("Broj dana: " + razlika);
		
		if(razlika < 2)
			{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}else
			{
		    flightRepository.delete(fs);
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
	
	
	
	@RequestMapping(value="/getUserFlightRes/{id}",method = RequestMethod.POST)
	ResponseEntity<List<FlightReservation>> getUserFlightRes(@PathVariable("id") Long id){
		System.out.println("USAAAO u listu flighttttt");
		
		User user = userRepository.findOneById(id);
		List<FlightReservation> lista=flightRepository.findAllByUserId(id);
		System.out.println(lista + "return lista rezervacija");
		
		return new ResponseEntity<>(lista,HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping(value="/getFlight/{id}",method = RequestMethod.POST)
	ResponseEntity<List<Flight>> getFlight(@PathVariable("id") Long id){
		System.out.println("USAAAO u listu flighttttt");
		Flight lista=fRepository.findOneById(id);
		List<Flight> lista2 = new ArrayList<>();
		lista2.add(lista);
		System.out.println(lista.getTake_off() + "NASAO LEEEEEEEEEEEEEEEEEEEt");
		
		return new ResponseEntity<>(lista2,HttpStatus.OK);
		
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
				res.setDayRez(new Date());
				Long razlika = getDateDiff(startDate,endDate,TimeUnit.DAYS);
				Long ukupno = null;
				ukupno = razlika*car.getPrice();
				res.setTotalPrice(ukupno);
				res.setNumDays(razlika.intValue());
				res.setUser(user);
				res.setCar(car);
				
				carReservationService.save(res);
				
				return new ResponseEntity<>(new CarReservationDTO(res),HttpStatus.CREATED);
				
				
			}else
			{
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		
	}
	
	
	public boolean reserved(Car c, Date pickupDate, Date returnDate) {
		
		//System.out.println("PRONADJI F AUTO" + carReservationService.allCarReservation(c.getId()));
		
		List<CarReservation> resCar = carReservationService.findAllByCar(c);
		
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
	
	
	@RequestMapping(value="/fastReservations/{idFlight}/{idCar}/{startDate}/{endDate}/{idUser}",method = RequestMethod.POST)
	public ResponseEntity<CarReservation> fastReservations(@PathVariable Long idFlight, @PathVariable Long idCar ,@PathVariable String startDate,
			@PathVariable String endDate, @PathVariable Long idUser) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date preuzimanje = null;
		Date vracanje = null;
		
		try {
			preuzimanje = dateFormat.parse(startDate);
			vracanje = dateFormat.parse(endDate);

			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		
		
		FlightReservation flightReservation = flightRepository.findByFlightId(idFlight);//nadji tu rezervaciju leta
		Car car = carRepository.findOneById(idCar);
		Discount disc = discountRepository.findOneByCar(car); //nadjem taj auto na popustu
		User user = userRepository.findOneById(idUser);
		
		CarReservation fastRes = new CarReservation(); //napravljena brza rezervacija
		fastRes.setCar(car);
		fastRes.setEndDate(vracanje);
		fastRes.setStartDate(preuzimanje);
		double price = car.getPrice();
		double discountPrice = disc.getDiscountprice();
		double totalPrice = price - (price*discountPrice)/100;
		
		fastRes.setTotalPrice(totalPrice);
		fastRes.setCategory(car.getCategory().getMark().toString());
		fastRes.setUser(user);
		
		carReservationService.save(fastRes);
		//u flight res se treba postaviti i brza rez vozila
		
		return new ResponseEntity<CarReservation>(fastRes,HttpStatus.OK);
		
	}
	

}
