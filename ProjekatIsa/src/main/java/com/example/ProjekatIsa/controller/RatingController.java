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

import com.example.ProjekatIsa.DTO.RatingAvioDTO;
import com.example.ProjekatIsa.DTO.RatingCarDTO;
import com.example.ProjekatIsa.DTO.RatingFlightDTO;
import com.example.ProjekatIsa.DTO.RatingHotelDTO;
import com.example.ProjekatIsa.DTO.RatingRentACarDTO;
import com.example.ProjekatIsa.DTO.RatingRoomDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.RatingCar;
import com.example.ProjekatIsa.model.RatingFlight;
import com.example.ProjekatIsa.model.RatingHotel;
import com.example.ProjekatIsa.model.RatingRentACar;
import com.example.ProjekatIsa.model.RatingRoom;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.AviocompanyRepository;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.repository.FlightRepository;
import com.example.ProjekatIsa.repository.HotelRepository;
import com.example.ProjekatIsa.repository.RatingAvioRepository;
import com.example.ProjekatIsa.repository.RatingCarRepository;
import com.example.ProjekatIsa.repository.RatingFlightRepository;
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
import com.example.ProjekatIsa.model.RatingAvio;
import com.example.ProjekatIsa.model.Aviocompany;



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
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	RatingFlightRepository ratingflightRepository;
	
	@Autowired
	RatingAvioRepository ratingAvioRepository;
	
	@Autowired
	AviocompanyRepository avioRepository;
	
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
		RatingCar o = ratingCarService.save(dodavanje);
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
		
		double nova = ocena.getRate();
		double stara = c.getAverage_rating();
		double ukupno = (nova + stara)/2;
		c.setAverage_rating(ukupno);
		try{
			Car izmenjeno = carService.save(c);
		}catch(NoSuchElementException e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(new RatingCarDTO(o), HttpStatus.CREATED);
		
		
	}
	
	
	
	@RequestMapping(value="rateAvio/{id}", method=RequestMethod.POST,consumes="application/json")
	ResponseEntity<RatingAvioDTO> rateAvio(@RequestBody RatingAvioDTO ocena,@PathVariable("id") Long id)throws Exception{
		System.out.println("USAO u rating servis " + ocena.getUser().getFirstName());
		
		List<RatingAvio> sveOcene = ratingAvioRepository.findAll();
		
		Flight ocenjen = new Flight();
		ocenjen = flightRepository.findOneById(id);
		System.out.println(ocenjen + "Pronasao leeeeeet");
		
		Aviocompany servis = new Aviocompany();
		
		servis = avioRepository.findOneByFlight(ocenjen);
		System.out.println(servis + "Servis je pronadjen");
		
		
		
		
		
		
	//provera da li je korisnik vec ocenio vozilo
		for(RatingAvio o : sveOcene){
			if(o.getUser().getId() == ocena.getUser().getId()  && o.getAvioCompany().getId() == ocena.getAviocompany().getId())
			{
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		}
		
		User u = new User(); 
	    u =	userRepository.findByFirstName(ocena.getUser().getFirstName());
		System.out.println("KORISNIK KOJI OCJENJUJE Avio" + u);
		//Car c = carService.findOneById(ocena.getCar().getId());
		System.out.println("avio KOJE OCJENJUJE KORISNIK" + u);

		
		RatingAvio dodavanje = new RatingAvio();
		dodavanje.setUser(u);
		dodavanje.setAvioCompany(servis);
		dodavanje.setRate(ocena.getRate());
		
		System.out.println("OCJENA" + dodavanje.getRate());
		//dodavanje ocene
		RatingAvio o = ratingAvioRepository.save(dodavanje);
		//azuriranje ocene vozila
		List<RatingAvio> noveOcene = ratingAvioRepository.findAll();
		int br = 0;
		int ocene = 0;
		for(RatingAvio ov : noveOcene)
		{
			if(ov.getAvioCompany().getId() == servis.getId())
			{
				br++;
				ocene += ov.getRate();
			}
		}
		
		
		double nova = ocena.getRate();
		double novaOcena =(nova + ocene)/ br;
		
		
		try{
			Aviocompany izmenjeno = avioRepository.save(servis);
		}catch(NoSuchElementException e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(new RatingAvioDTO(o), HttpStatus.CREATED);
		
		
	}
	
	//////////////////////////////////////////////////////////
	@RequestMapping(value="rateService/{id}", method=RequestMethod.POST,consumes="application/json")
	ResponseEntity<RatingRentACarDTO> rateService(@RequestBody RatingRentACarDTO ocena,@PathVariable("id") Long id)throws Exception{
		System.out.println("USAO u rating servis " + ocena.getUser().getFirstName());
		
		List<RatingRentACar> sveOcene = servisCarRepository.findAll();
		
		RentACar ocenjen = new RentACar();
		ocenjen = rentalRepository.findOneById(id);
		System.out.println(ocenjen + "Pronasao servis koji ocjenjujemo");
		
		//RentACar servis = new RentACar();
		
		//s//ervis = rentalRepository.findOneByCar(ocenjen);
		//System.out.println(servis + "Servis je pronadjen");
		
		
		
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
		dodavanje.setCar(ocenjen);
		dodavanje.setRate(ocena.getRate());
		
		System.out.println("OCJENA" + dodavanje.getRate());
		//dodavanje ocene
		RatingRentACar o = servisCarRepository.save(dodavanje);
		//azuriranje ocene vozila
		List<RatingRentACar> noveOcene = servisCarRepository.findAll();
		int br = 0;
		int ocene = 0;
		int nova = ocena.getRate();
		for(RatingRentACar ov : noveOcene)
		{
			if(ov.getCar().getId() == ocenjen.getId())
			{
				br++;
				ocene += ov.getRate();
			}
		}
		double stara = ocenjen.getAverage_rating();
		double novaOcena = (stara+ocena.getRate()) / 2;
		ocenjen.setAverage_rating(novaOcena);
		try{
			RentACar izmenjeno = rentalRepository.save(ocenjen);
		}catch(NoSuchElementException e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(new RatingRentACarDTO(o), HttpStatus.CREATED);
		
		
	}
	//////////////////////////////////////////////////////
	
	
	
	
	@RequestMapping(value="rateHotel/{id}", method=RequestMethod.POST,consumes="application/json")
	ResponseEntity<RatingHotelDTO> rateHotel(@RequestBody RatingHotelDTO ocena,@PathVariable("id") Long id)throws Exception{
		System.out.println("USAO u rating servis " + ocena.getUser().getFirstName());
		
		List<RatingHotel> sveOcene = ratingHotelRepository.findAll();
		
		Hotel hotel = new Hotel();
		hotel = hotelRepository.findOneById(id);
		
		System.out.println(hotel.getName() + "Hotel je pronadjen");
		
		
		
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
		
		double nova = ocena.getRate();
		double stara = hotel.getAverage_rating();
		double ukupno = (nova+stara)/2;
		hotel.setAverage_rating(ukupno);
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
		
		double nova = ocena.getRate();
		double stara = c.getRoom_average_rating();
		double ukupno = (nova+stara)/2;
		c.setRoom_average_rating(ukupno);
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
	
	
	@RequestMapping(value="userFlightRating/{id}", method=RequestMethod.GET)
	ResponseEntity<List<RatingFlightDTO>> oceneKorisnika3(@PathVariable("id") Long id)
	{
		User user = userRepository.findOneById(id);
		List<RatingFlight> ocene = ratingflightRepository.findAllByUser(user);
		List<RatingFlightDTO> oceneDTO = new ArrayList<>();
		
		for(RatingFlight o : ocene){
			oceneDTO.add(new RatingFlightDTO(o));
		}
		
		System.out.println("OCENE LETOVA TRENUTNOG KORISNIKA" + oceneDTO.size());
		return new ResponseEntity<>(oceneDTO,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="userAvioRating/{id}", method=RequestMethod.GET)
	ResponseEntity<List<RatingAvioDTO>> oceneKorisnika4(@PathVariable("id") Long id)
	{
		User user = userRepository.findOneById(id);
		List<RatingAvio> ocene = ratingAvioRepository.findAllByUser(user);
		List<RatingAvioDTO> oceneDTO = new ArrayList<>();
		
		for(RatingAvio o : ocene){
			oceneDTO.add(new RatingAvioDTO(o));
		}
		
		System.out.println("OCENE AVIOKOMP TRENUTNOG KORISNIKA" + oceneDTO.size());
		return new ResponseEntity<>(oceneDTO,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="userServiceRating/{id}", method=RequestMethod.GET)
	ResponseEntity<List<RatingRentACarDTO>> userServiceRating(@PathVariable("id") Long id)
	{
		
		User user = userRepository.findOneById(id);
		System.out.println(id + "IDDDDDDDDDDDDDDD");
		List<RatingRentACar> ocene = servisCarRepository.findAllByUser(user);
		System.out.println(ocene.size());
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
	
	
	
	@RequestMapping(value="rateFlight/{id}", method=RequestMethod.POST)
	ResponseEntity<RatingFlightDTO> rateFlight(@RequestBody RatingFlightDTO ocena)throws Exception{
		System.out.println("USAO " + ocena.getUser().getFirstName() + " ");
		
		List<RatingFlight> sveOcene = ratingflightRepository.findAll();
		
		//provera da li je korisnik vec ocenio vozilo
		for(RatingFlight o : sveOcene){
			if(o.getUser().getId() == ocena.getUser().getId()  && o.getFlight().getId() == ocena.getFlight().getId())
			{
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		}
		Long id;
		id = ocena.getUser().getId();
		System.out.println("ID KORISNIKA" + id);
		User u = new User(); 
	    u =	userRepository.findByFirstName(ocena.getUser().getFirstName());
		System.out.println("KORISNIK KOJI OCJENJUJE let" + u);
		Flight c = flightRepository.findOneById(ocena.getFlight().getId());
		System.out.println("let KOJE OCJENJUJE KORISNIK" + u);

		
		RatingFlight dodavanje = new RatingFlight();
		dodavanje.setUser(u);
		dodavanje.setFlight(c);
		dodavanje.setRate(ocena.getRate());
		
		System.out.println("OCJENA" + dodavanje.getRate());
		//dodavanje ocene
		RatingFlight o = ratingflightRepository.save(dodavanje);
		//azuriranje ocene vozila
		List<RatingFlight> noveOcene = ratingflightRepository.findAll();
		int br = 0;
		int ocene = 0;
		for(RatingFlight ov : noveOcene)
		{
			if(ov.getFlight().getId() == c.getId())
			{
				br++;
				ocene += ov.getRate();
			}
		}
		
		double nova = ocena.getRate();
		double stara = c.getAverageRating();
		double ukupno = (nova+stara)/2;
		c.setAverageRating(ukupno);
		//c.setRoom_average_rating(novaOcena);
		try{
			Flight izmenjeno = flightRepository.save(c);
		}catch(NoSuchElementException e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(new RatingFlightDTO(o), HttpStatus.CREATED);
		
		
	}


}
