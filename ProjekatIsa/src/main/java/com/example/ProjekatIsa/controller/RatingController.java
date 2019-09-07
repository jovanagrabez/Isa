package com.example.ProjekatIsa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.RatingCarDTO;
import com.example.ProjekatIsa.DTO.RatingHotelDTO;
import com.example.ProjekatIsa.DTO.RatingRentACarDTO;
import com.example.ProjekatIsa.DTO.RatingRoomDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.RatingCar;
import com.example.ProjekatIsa.model.RatingHotel;
import com.example.ProjekatIsa.model.RatingRentACar;
import com.example.ProjekatIsa.model.RatingRoom;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.repository.HotelRepository;
import com.example.ProjekatIsa.repository.RatingCarRepository;
import com.example.ProjekatIsa.repository.RatingHotelRepository;
import com.example.ProjekatIsa.repository.RatingRentACarRepository;
import com.example.ProjekatIsa.repository.RatingRoomRepository;
import com.example.ProjekatIsa.repository.RentalCarRepository;
import com.example.ProjekatIsa.repository.RoomRepository;
import com.example.ProjekatIsa.repository.UserRepository;
import com.example.ProjekatIsa.service.CarService;
import com.example.ProjekatIsa.service.RatingCarService;
import com.example.ProjekatIsa.service.RatingRoomService;
import com.example.ProjekatIsa.service.RoomService;
import com.example.ProjekatIsa.service.UserService;


@RestController
@RequestMapping("rating")
@CrossOrigin(origins = "http://localhost:4200")
public class RatingController {
	
	@Autowired 
	RatingCarService ratingCarService;
	
	@Autowired 
	UserService userService;
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired 
	CarService carService;
	
	@Autowired 
	RoomService roomService;
	
	@Autowired 
	RoomRepository roomRepository;
	
	@Autowired
	RatingCarRepository ratingCarRepository;
	
	@Autowired
	RatingRentACarRepository servisCarRepository;
	
	@Autowired
	RatingRoomRepository ratingRoomRepository;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	RentalCarRepository rentalRepository;
	
	@Autowired
	RatingHotelRepository ratingHotelRepository;
	
	@Autowired
	HotelRepository hotelRepository;
	
	@RequestMapping(value="rateCar", method=RequestMethod.POST,consumes="application/json")
	ResponseEntity<RatingCarDTO> rateCar(@RequestBody RatingCarDTO ocena)throws Exception{
		System.out.println("USAO " + ocena.getUser().getFirstName() + " " + ocena.getCar().getName());
		
		List<RatingCar> sveOcene = ratingCarService.getAll();
		
		//provera da li je korisnik vec ocenio vozilo
		for(RatingCar o : sveOcene){
			if(o.getUser().getId() == ocena.getUser().getId()  && o.getCar().getId() == ocena.getCar().getId())
			{
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		}
		Long id;
		id = ocena.getUser().getId();
		System.out.println("ID KORISNIKA" + id);
		User u = new User(); 
	    u =	userRepository.findByFirstName(ocena.getUser().getFirstName());
		System.out.println("KORISNIK KOJI OCJENJUJE VOZILO" + u);
		Car c = carService.findOneById(ocena.getCar().getId());
		System.out.println("VOZILO KOJE OCJENJUJE KORISNIK" + u);

		
		RatingCar dodavanje = new RatingCar();
		dodavanje.setUser(u);
		dodavanje.setCar(c);
		dodavanje.setRate(ocena.getRate());
		
		System.out.println("OCJENA" + dodavanje.getRate());
		//dodavanje ocene
		RatingCar o = ratingCarRepository.save(dodavanje);
		//azuriranje ocene vozila
		List<RatingCar> noveOcene = ratingCarService.getAll();
		//Car ocenaVozila = carRepository.findOneById(id)
		int br = 0;
		int ocene = 0;
		for(RatingCar ov : noveOcene)
		{
			if(ov.getCar().getId() == c.getId())
			{
				br++;
				ocene += ov.getRate();
			}
		}
		
		double novaOcena = ocene / br;
		double ocenaVozila = c.getAverage_rating();
		double ukupno;
		ukupno = (ocene + ocenaVozila)/br;
		c.setAverage_rating(novaOcena);
		try{
			Car izmenjeno = carService.save(c);
		}catch(NoSuchElementException e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(new RatingCarDTO(o), HttpStatus.CREATED);
		
		
	}
	
	
	
	@RequestMapping(value="rateService/{id}", method=RequestMethod.POST,consumes="application/json")
	ResponseEntity<RatingRentACarDTO> rateService(@RequestBody RatingRentACarDTO ocena,@PathVariable("id") Long id)throws Exception{
		System.out.println("USAO u rating servis " + ocena.getUser().getFirstName());
		
		List<RatingRentACar> sveOcene = servisCarRepository.findAll();
		
		Car ocenjen = new Car();
		ocenjen = carService.findOneById(id);
		System.out.println(ocenjen + "Pronasao vozilo");
		
		RentACar servis = new RentACar();
		
		servis = rentalRepository.findOneByCar(ocenjen);
		System.out.println(servis + "Servis je pronadjen");
		
		
		
	//provera da li je korisnik vec ocenio vozilo
		for(RatingRentACar o : sveOcene){
			if(o.getUser().getId() == ocena.getUser().getId()  && o.getCar().getId() == ocena.getCar().getId())
			{
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		}
		
		User u = new User(); 
	    u =	userRepository.findByFirstName(ocena.getUser().getFirstName());
		System.out.println("KORISNIK KOJI OCJENJUJE VOZILO" + u);
		//Car c = carService.findOneById(ocena.getCar().getId());
		System.out.println("VOZILO KOJE OCJENJUJE KORISNIK" + u);

		
		RatingRentACar dodavanje = new RatingRentACar();
		dodavanje.setUser(u);
		dodavanje.setCar(servis);
		dodavanje.setRate(ocena.getRate());
		
		System.out.println("OCJENA" + dodavanje.getRate());
		//dodavanje ocene
		RatingRentACar o = servisCarRepository.save(dodavanje);
		//azuriranje ocene vozila
		List<RatingRentACar> noveOcene = servisCarRepository.findAll();
		int br = 0;
		int ocene = 0;
		for(RatingRentACar ov : noveOcene)
		{
			if(ov.getCar().getId() == servis.getId())
			{
				br++;
				ocene += ov.getRate();
			}
		}
		
		double novaOcena = ocene / br;
		servis.setAverage_rating(novaOcena);
		try{
			RentACar izmenjeno = rentalRepository.save(servis);
		}catch(NoSuchElementException e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(new RatingRentACarDTO(o), HttpStatus.CREATED);
		
		
	}
	
	
	
	
	
	@RequestMapping(value="rateHotel/{id}", method=RequestMethod.POST,consumes="application/json")
	ResponseEntity<RatingHotelDTO> rateHotel(@RequestBody RatingHotelDTO ocena,@PathVariable("id") Long id)throws Exception{
		System.out.println("USAO u rating servis " + ocena.getUser().getFirstName());
		
		List<RatingHotel> sveOcene = ratingHotelRepository.findAll();
		
		Room soba = new Room();
		soba = roomService.findOneById(id);
		System.out.println(soba + "Pronasao vozilo");
		
		Hotel hotel = new Hotel();
		
		hotel = hotelRepository.findOneByRooms(soba);
		System.out.println(hotel + "Hotel je pronadjen");
		
		
		
	//provera da li je korisnik vec ocenio vozilo
		for(RatingHotel o : sveOcene){
			if(o.getUser().getId() == ocena.getUser().getId())
			{
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		}
		
		User u = new User(); 
	    u =	userRepository.findByFirstName(ocena.getUser().getFirstName());
		System.out.println("KORISNIK KOJI OCJENJUJE VOZILO" + u);
		//Car c = carService.findOneById(ocena.getCar().getId());
		System.out.println("VOZILO KOJE OCJENJUJE KORISNIK" + u);

		
		RatingHotel dodavanje = new RatingHotel();
		dodavanje.setUser(u);
		dodavanje.setHotel(hotel);
		dodavanje.setRate(ocena.getRate());
		
		System.out.println("OCJENA" + dodavanje.getRate());
		//dodavanje ocene
		RatingHotel o = ratingHotelRepository.save(dodavanje);
		//azuriranje ocene vozila
		List<RatingHotel> noveOcene = ratingHotelRepository.findAll();
		int br = 0;
		int ocene = 0;
		for(RatingHotel ov : noveOcene)
		{
			if(ov.getHotel().getId() == hotel.getId())
			{
				br++;
				ocene += ov.getRate();
			}
		}
		
		double novaOcena = ocene / br;
		hotel.setAverage_rating(novaOcena);
		try{
			Hotel izmenjeno = hotelRepository.save(hotel);
		}catch(NoSuchElementException e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(new RatingHotelDTO(o), HttpStatus.CREATED);
		
		
	}
	
	
	@RequestMapping(value="rateRoom", method=RequestMethod.POST)
	ResponseEntity<RatingRoomDTO> rateRoom(@RequestBody RatingRoomDTO ocena)throws Exception{
		System.out.println("USAO " + ocena.getUser().getFirstName() + " ");
		
		List<RatingRoom> sveOcene = ratingRoomRepository.findAll();
		
		//provera da li je korisnik vec ocenio vozilo
		for(RatingRoom o : sveOcene){
			if(o.getUser().getId() == ocena.getUser().getId()  && o.getRoom().getId() == ocena.getRoom().getId())
			{
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		}
		Long id;
		id = ocena.getUser().getId();
		System.out.println("ID KORISNIKA" + id);
		User u = new User(); 
	    u =	userRepository.findByFirstName(ocena.getUser().getFirstName());
		System.out.println("KORISNIK KOJI OCJENJUJE SOBU" + u);
		Room c = roomService.findOneById(ocena.getRoom().getId());
		System.out.println("SOBA KOJE OCJENJUJE KORISNIK" + u);

		
		RatingRoom dodavanje = new RatingRoom();
		dodavanje.setUser(u);
		dodavanje.setRoom(c);
		dodavanje.setRate(ocena.getRate());
		
		System.out.println("OCJENA" + dodavanje.getRate());
		//dodavanje ocene
		RatingRoom o = ratingRoomRepository.save(dodavanje);
		//azuriranje ocene vozila
		List<RatingRoom> noveOcene = ratingRoomRepository.findAll();
		int br = 0;
		int ocene = 0;
		for(RatingRoom ov : noveOcene)
		{
			if(ov.getRoom().getId() == c.getId())
			{
				br++;
				ocene += ov.getRate();
			}
		}
		
		double novaOcena = ocene / br;
		c.setRoom_average_rating(novaOcena);
		try{
			Room izmenjeno = roomRepository.save(c);
		}catch(NoSuchElementException e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(new RatingRoomDTO(o), HttpStatus.CREATED);
		
		
	}
	
	
	@RequestMapping(value="userCarRating/{id}", method=RequestMethod.GET)
	ResponseEntity<List<RatingCarDTO>> oceneKorisnika(@PathVariable("id") Long id)
	{
		User user = userRepository.findOneById(id);
		List<RatingCar> ocene = ratingCarRepository.findAllByUser(user);
		List<RatingCarDTO> oceneDTO = new ArrayList<>();
		
		for(RatingCar o : ocene){
			oceneDTO.add(new RatingCarDTO(o));
		}
		
		System.out.println("OCENE VOZILA TRENUTNOG KORISNIKA" + oceneDTO.size());
		return new ResponseEntity<>(oceneDTO,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="userServiceRating/{id}", method=RequestMethod.GET)
	ResponseEntity<List<RatingRentACarDTO>> userServiceRating(@PathVariable("id") Long id)
	{
		User user = userRepository.findOneById(id);
		List<RatingRentACar> ocene = servisCarRepository.findAllByUser(user);
		List<RatingRentACarDTO> oceneDTO = new ArrayList<>();
		
		for(RatingRentACar o : ocene){
			oceneDTO.add(new RatingRentACarDTO(o));
		}
		
		System.out.println("OCENE SERVISA TRENUTNOG KORISNIKA: " + oceneDTO.size());
		return new ResponseEntity<>(oceneDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="userRoomRating/{id}", method=RequestMethod.GET)
	ResponseEntity<List<RatingRoomDTO>> oceneKorisnika2(@PathVariable("id") Long id)
	{
		User user = userRepository.findOneById(id);
		List<RatingRoom> ocene = ratingRoomRepository.findAllByUser(user);
		List<RatingRoomDTO> oceneDTO = new ArrayList<>();
		
		for(RatingRoom o : ocene){
			oceneDTO.add(new RatingRoomDTO(o));
		}
		
		System.out.println("OCENE SOBA TRENUTNOG KORISNIKA: " + oceneDTO.size());
		return new ResponseEntity<>(oceneDTO,HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="userHotelRating/{id}", method=RequestMethod.GET)
	ResponseEntity<List<RatingHotelDTO>> userHotelRating(@PathVariable("id") Long id)
	{
		User user = userRepository.findOneById(id);
		List<RatingHotel> ocene = ratingHotelRepository.findAllByUser(user);
		List<RatingHotelDTO> oceneDTO = new ArrayList<>();
		
		for(RatingHotel o : ocene){
			oceneDTO.add(new RatingHotelDTO(o));
		}
		
		System.out.println("OCENE HOTELA TRENUTNOG KORISNIKA: " + oceneDTO.size());
		return new ResponseEntity<>(oceneDTO,HttpStatus.OK);
	}


}
