package com.example.ProjekatIsa.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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

import com.example.ProjekatIsa.DTO.CarReservationDTO;
import com.example.ProjekatIsa.DTO.HotelDTO;
import com.example.ProjekatIsa.DTO.ReservationRoomDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.Discount;
import com.example.ProjekatIsa.model.DiscountHotel;
import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.RatingHotel;
import com.example.ProjekatIsa.model.RatingRoom;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.SearchFormServices;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.DiscountHotelRepository;
import com.example.ProjekatIsa.repository.FlightRepository;
import com.example.ProjekatIsa.repository.FlightReservationRepository;
import com.example.ProjekatIsa.repository.HotelRepository;
import com.example.ProjekatIsa.repository.RatingRoomRepository;
import com.example.ProjekatIsa.repository.ReservationRoomRepository;
import com.example.ProjekatIsa.repository.RoomRepository;
import com.example.ProjekatIsa.repository.UserRepository;
import com.example.ProjekatIsa.service.HotelService;
import com.example.ProjekatIsa.service.ReservationRoomService;
import com.example.ProjekatIsa.service.RoomService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired 
	private ReservationRoomRepository reservationRoomRepository;
	
	@Autowired 
	private ReservationRoomService reservationRoomService;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private RatingRoomRepository ratingRoomRepository;
	
	@Autowired
	private DiscountHotelRepository dhRepository;
	
	@Autowired
	private FlightReservationRepository flightRepository;
	
	@Autowired
	private FlightRepository fRepository;
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Room>  getRooms() {
		
		System.out.println("Number of rooms: " + roomService.getAll().size());
		
		return roomService.getAll();
	}
	
	@RequestMapping(value="/searchRooms/{id}/{cenaod}/{cenado}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchRooms(@RequestBody ReservationRoom roomReservation,
										@PathVariable("id") Long id,
										@PathVariable("cenaod") Long cenaOd,
										@PathVariable("cenado") Long cenaDo){
		
		System.out.println("Dosao u search rooms");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                Locale.ENGLISH); 
		   Date startDate = null;
		   Date endDate = null;
		   
		   try {
			   startDate = sdf.parse(roomReservation.getStartDate().toString());
			   endDate = sdf.parse(roomReservation.getEndDate().toString());
		   } catch (ParseException e) {
			   e.printStackTrace();
			   
		   }
		Hotel hotel = hotelRepository.findOneById(id);
		List<Room> allRooms = roomRepository.findAllByHotel(hotel);
		
		List<Room> returnList = new ArrayList<Room>();
		
		for (Room r : allRooms) {
			List<ReservationRoom> reservationRoom = reservationRoomRepository.findAllByRoom(r);
			boolean free = true;
			
			int reserved = 0;
			if (!reservationRoom.isEmpty()) {
				for(ReservationRoom res : reservationRoom) {
					
					free = checkforfree(res, endDate, startDate);
					if (!free) {
						reserved ++;
					}
				}
			}
			if (reserved == 0) {
				if (cenaOd == -1 && cenaDo == -1) {
					if (r.getRoom_description().equals(roomReservation.getCategory())) {
						returnList.add(r);
					}
				}
				else if (cenaOd==-1) {
					if (r.getRoom_description().equals(roomReservation.getCategory()) && r.getPrice() >= cenaOd) {
						returnList.add(r);
					}
				}
				else {
					if (r.getRoom_description().equals(roomReservation.getCategory()) && r.getPrice() >= cenaOd && r.getPrice() <= cenaDo) {
						returnList.add(r);
					}
				}
			}
		}
		
		return new ResponseEntity<List<Room>>(returnList,HttpStatus.OK);
	
	}
	
	public boolean checkforfree(ReservationRoom res, Date endDate, Date startDate) {
		
		//provjeravamo datum koji smo unijeli za preuzimanje vozila
		//ako je on nakon datuma vracanja vozila koji je registrovan -ok
		if(startDate.getTime() >= res.getEndDate().getTime()) {
			return true;
			
		}
		else {
			if(endDate.getTime() <= res.getStartDate().getTime())
			{
				return true;
			}
			else {
				return false;
			}
		}
	}
	//@PreAuthorize("hasAuthority('bookRoom')")
	
	@RequestMapping(value="/bookRoom",
			method = RequestMethod.POST,
			consumes =MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> bookRoom(@RequestBody ReservationRoomDTO roomRes){
		System.out.println("Usao u bookRoom");
		
		Date startDate = null;
		Date endDate = null;
		
		startDate = roomRes.getStartDate();
		endDate = roomRes.getEndDate();
		
		Room room = roomRepository.findOneById(roomRes.getRoom().getId());
		User user = userRepository.findOneById(roomRes.getUser().getId());
		
		ReservationRoom returnValue = new ReservationRoom();
		
		if (!reserved(room, startDate, endDate)) {
			returnValue.setStartDate(roomRes.getStartDate());
			returnValue.setEndDate(roomRes.getEndDate());
			returnValue.setNumPeople(roomRes.getNumPeople());
			returnValue.setUser(user);
			returnValue.setTotalPrice(roomRes.getTotalPrice());
			returnValue.setCategory(roomRes.getCategory());
			returnValue.setRoom(room);
			
			reservationRoomService.save(returnValue);
			return new ResponseEntity<>(HttpStatus.CREATED);
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		

	}
public boolean reserved(Room r, Date startDate, Date endDate) {
				
		List<ReservationRoom> resRoom = reservationRoomRepository.findAllByRoom(r);
		
		for(ReservationRoom reservation : resRoom) {
			
			if(endDate.getTime() >= reservation.getStartDate().getTime() && endDate.getTime()<= reservation.getEndDate().getTime())
			{
				return true;
			} else if(startDate.getTime() >= reservation.getStartDate().getTime() && startDate.getTime() <= reservation.getEndDate().getTime())
			{
				return true;
			}
		}
		return false;
	}
	
	@PreAuthorize("hasAuthority('getRatingRoom')")
	@RequestMapping(value="/getRatingRoom/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RatingRoom>>  getRatingRoom(@PathVariable("id") Long idRoom) {

		List<RatingRoom> returnList = new ArrayList<RatingRoom>();
		Room room = roomService.findOneById(idRoom);
		returnList = ratingRoomRepository.findAllByRoom(room);
		
		return new ResponseEntity<List<RatingRoom>>(returnList,HttpStatus.OK);

	}
	@RequestMapping(value="/countAverageRating/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Double countAverageRating(@PathVariable("id") Long idRoom) {

		Double finalCount = 0.0;
		int sum = 0;
		List<RatingRoom> returnList = new ArrayList<RatingRoom>();
		Room room = roomService.findOneById(idRoom);
		returnList = ratingRoomRepository.findAllByRoom(room);
		for (RatingRoom rr:returnList) {
			sum += rr.getRate();
		}
		if (!returnList.isEmpty()) {
			finalCount = (double) (sum/returnList.size());
		}
		return finalCount;

	}
	
	@RequestMapping(value="/isReserved/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean isReserved(@PathVariable("id") Long idRoom) {

		Date today=new Date();
				
		List<ReservationRoom> returnList = new ArrayList<ReservationRoom>();
		Room room = roomService.findOneById(idRoom);
		returnList = reservationRoomRepository.findAllByRoom(room);
		
		if(!returnList.isEmpty()) {
			for (ReservationRoom rr: returnList) {
				if(today.getTime() <= rr.getStartDate().getTime() && today.getTime()<= rr.getEndDate().getTime())
				{
					System.out.println("pronsasao1  " + rr.getStartDate());
					return true;
				} 
			}
		}
		
		return false;

	}
	
	@RequestMapping(
			value = "/getDiscountRooms/{city}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DiscountHotel>>  getDiscountRooms(@PathVariable("city") String city) {
		
		List<Hotel> hotels = hotelRepository.findAllByCity(city);
		List<DiscountHotel> pomocni = new ArrayList<DiscountHotel>();
		List<DiscountHotel> returnValue = new ArrayList<DiscountHotel>();
		
		if (!hotels.isEmpty()) {
			
			for(Hotel h : hotels) {
				pomocni = dhRepository.findAllByHotel(h);
				if (!pomocni.isEmpty()) {
					for(DiscountHotel dh: pomocni) {
						returnValue.add(dh);
					}
				}
			}
		}
		
		
		
		System.out.println("Pronasao je discount soba: " + returnValue.size());
		return new ResponseEntity<List<DiscountHotel>>(returnValue, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/fastReservationsHotel/{idFlight}/{idRoom}/{startDate}/{endDate}/{idUser}",
			method = RequestMethod.GET)
	public ResponseEntity<ReservationRoom> fastReservationsHotel(@PathVariable Long idFlight, @PathVariable Long idRoom ,@PathVariable String startDate,
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
		//Car car = carRepository.findOneById(idCar);
		Room room = roomRepository.findOneById(idRoom);
		DiscountHotel disc = dhRepository.findOneByRoom(room); //nadjem tu sobu na popustu
		User user = userRepository.findOneById(idUser);
		
		//CarReservation fastRes = new CarReservation(); //napravljena brza rezervacija
		ReservationRoom fastRes = new ReservationRoom();
		fastRes.setRoom(room);
		fastRes.setEndDate(vracanje);
		fastRes.setStartDate(preuzimanje);
		double price = room.getPrice();
		double discountPrice = disc.getDiscountprice();
		double totalPrice = price - (price*discountPrice)/100;
		
		fastRes.setTotalPrice(totalPrice);
		fastRes.setCategory(room.getRoom_description());
		fastRes.setUser(user);
		
		reservationRoomService.save(fastRes);
		//u flight res se treba postaviti i brza rez vozila
		
		return new ResponseEntity<ReservationRoom>(fastRes,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/searchFast",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchFast(@RequestBody SearchFormServices searchForm){
		System.out.println("Dosao u search rooooom");
		
		List<DiscountHotel> povratna= new ArrayList<DiscountHotel>();
		
		//List<RentACar> all = rentalcarService.getAll();
		List<Hotel> all = hotelRepository.findAll();
		Set<Hotel> returnList = new HashSet<Hotel>();
		List<Hotel> returnList2 = new ArrayList<Hotel>();
		List<Hotel> returnList3 = new ArrayList<Hotel>();

		//pretraga po datumu
		
		if (searchForm.getStartDate()!=null && searchForm.getEndDate()!=null) {
			for (Hotel hot : all) {
				System.out.println("usao u prvu petlju : svih hotela");
				System.out.println("servis ima soba: " + hot.getRooms().size());
				//pronalazim sve sobe hotela
				List<Room> rooms = roomRepository.findAllByHotel(hot);
				if (rooms.size()>0) {
					int num = rooms.size();
					System.out.println("broj soba " + rooms.size());
					for (Room r : rooms) {
						if(reserved(r, searchForm.getStartDate(), searchForm.getEndDate())) {
							System.out.println("umanjujem sobu");
							--num;
						}
					}
					if(num!=0) {
						System.out.println("Dodajem hotel");
						returnList3.add(hot);
					}
				}
			}
		}else {
			returnList3 = hotelRepository.findAll();
		}

		//ako se pretrazuje po nazivu
		if (searchForm.getNameHotel() != null) {
			for (Hotel h : returnList3) {
				if (h.getName().contains(searchForm.getNameHotel())) {
					returnList.add(h);
				}
			}
			//ako se pretrazuje i po nazivu i po gradu
			if (searchForm.getCity() != null) {
				for (Hotel h : returnList) {
					if (h.getCity().equals(searchForm.getCity())) {
						returnList2.add(h);
					}
				}
			}
			else {
			}
		}
		//ako se ne pretrazuje po nazivu nego samo gradu 
		else {
			if (searchForm.getCity() != null) {
				for (Hotel h : returnList3) {
					if (h.getCity().equals(searchForm.getCity())) {
						returnList.add(h);
					}
				}
			}
		}
		
		if (!returnList.isEmpty()) {
			for(Hotel hot: returnList) {
				List<DiscountHotel> dh = dhRepository.findAllByHotel(hot);
				povratna.addAll(dh);
			}
		}
		
		return new ResponseEntity<List<DiscountHotel>>(povratna, HttpStatus.OK);
	}
}
