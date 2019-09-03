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

import com.example.ProjekatIsa.DTO.AdditionalServiceForHotelDTO;
import com.example.ProjekatIsa.DTO.HotelDTO;
import com.example.ProjekatIsa.DTO.RoomDTO;
import com.example.ProjekatIsa.model.AdditionalServiceForHotel;
import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.repository.AdditionalServiceForHotelRepository;
import com.example.ProjekatIsa.repository.HotelRepository;
import com.example.ProjekatIsa.repository.RoomRepository;
import com.example.ProjekatIsa.service.AdditionalServiceForHotelService;
import com.example.ProjekatIsa.service.HotelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@Autowired 
	private HotelRepository hotelRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private AdditionalServiceForHotelRepository addRepository;
	
	@Autowired
	private AdditionalServiceForHotelService addService;
	
	//@Autowired
	//private RatingHotelRepository ratingHotelService;
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel>  getHotels() {
		
		System.out.println("Number of hotels: " + hotelService.getAll().size());
		
		return hotelService.getAll();
	}
	
	@RequestMapping(
			value = "/getAllRooms/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRooms(@PathVariable("id") Long id) {
		List<Room> returnList = new ArrayList<Room> ();
		Hotel h = hotelRepository.findOneById(id);
		returnList = roomRepository.findAllByHotel(h);
		if (returnList!=null) {
	        return new ResponseEntity<List<Room>>(returnList,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(
			value = "/getAllServices/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getServices(@PathVariable("id") Long id) {
		List<AdditionalServiceForHotel> returnList = new ArrayList<AdditionalServiceForHotel> ();
		Hotel h = hotelRepository.findOneById(id);
		returnList = addRepository.findAllByHotel(h);
		if (returnList!=null) {
	        return new ResponseEntity<List<AdditionalServiceForHotel>>(returnList,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('addHotel')")
	@RequestMapping(value="/addHotel",
			method = RequestMethod.POST)
	public ResponseEntity<?> addNewHotel(@RequestBody HotelDTO hotel){
		System.out.println("Dosao u add hotel");
		
		 Hotel h = this.hotelService.addHotel(new Hotel(hotel));
		
	        return new ResponseEntity<Hotel>(h,HttpStatus.OK);
		
	}
	
	
	@PreAuthorize("hasAuthority('changeHotel')")
	@RequestMapping(value="/changeHotel/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> changeHotel(@RequestBody HotelDTO newHotel,
										@PathVariable("id") Long id){
		System.out.println("Dosao u change hotel");
		
		Hotel oldHotel = hotelRepository.findOneById(id);
		
		if (oldHotel!=null) {
		if (newHotel.getAddress()!=null) {
			oldHotel.setAddress(newHotel.getAddress());
		}
		if (newHotel.getName()!=null) {
			oldHotel.setName(newHotel.getName());
		}
		if(newHotel.getDescription()!=null) {
			oldHotel.setDescription(newHotel.getDescription());
		}
		hotelRepository.save(oldHotel);
		
	        return new ResponseEntity<Hotel>(oldHotel,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('deleteHotel')")
	@RequestMapping(value="/deleteHotel",
			method = RequestMethod.POST)
	public ResponseEntity<?> deleteHotel(@RequestBody Long id){
		System.out.println("Dosao u delete hotel");
		
		Hotel h = hotelRepository.findOneById(id);
		if (h!=null) {
			boolean obrisan = hotelService.deleteHotel(h);
			if (obrisan = true ) {
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PreAuthorize("hasAuthority('addService')")
	@RequestMapping(value="/addService/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> addService(@RequestBody AdditionalServiceForHotelDTO newService,
										@PathVariable("id") Long id){
		System.out.println("Dosao u add service hotel");
		
		Hotel hotel = hotelRepository.findOneById(id);
		AdditionalServiceForHotel a =new AdditionalServiceForHotel(newService);
		
		//dodavanje u model
		hotel.addAdditionalService(a);
		a.setHotel(hotel);
		
		//cuvanje u bazu
		this.addService.addService(a);
		hotelRepository.save(hotel);
			return new ResponseEntity<>(null, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasAuthority('addRoom')")
	@RequestMapping(value="/addRoom/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> addRoom(@RequestBody RoomDTO newRoom,
										@PathVariable("id") Long id){
		System.out.println("Dosao u add room hotel");
		
		Hotel hotel = hotelRepository.findOneById(id);
		Room r =new Room(newRoom);
		r.setNumber(hotel.getRooms().size()+1);
		//dodavanje u model
		hotel.addRoom(r);
		r.setHotel(hotel);
		
		//cuvanje u bazu
		this.roomRepository.save(r);
		hotelRepository.save(hotel);
			return new ResponseEntity<>(null, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasAuthority('getAdditionalServices')")
	@RequestMapping(value="/getAllAdditionalServices",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllAdditionalServices(){
		System.out.println("Dosao u getAllAdditionalServices");
		List<AdditionalServiceForHotel> returnList = new ArrayList<AdditionalServiceForHotel>();
		returnList = addService.getAll();
	    
		if(returnList!=null) {
			return new ResponseEntity<List<AdditionalServiceForHotel>>(returnList,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		
	}
	//dovrsiti
	@PreAuthorize("hasAuthority('deleteService')")
	@RequestMapping(value="/deleteService/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> deleteService(@RequestBody AdditionalServiceForHotel add,
										@PathVariable("id") Long id){
		System.out.println("Dosao u change hotel");
		
		Hotel h = hotelRepository.findOneById(id);
		
	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
	}
	
}
