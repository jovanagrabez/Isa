package com.example.ProjekatIsa.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.bouncycastle.crypto.agreement.DHStandardGroups;
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
import com.example.ProjekatIsa.DTO.RoomDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.Discount;
import com.example.ProjekatIsa.model.DiscountHotel;
import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Pricing;
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
import com.example.ProjekatIsa.service.PricingService;

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
	
	@Autowired 
	private PricingService pricingService;
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Room>  getRooms() {
		
		System.out.println("Number of rooms: " + roomService.getAll().size());
		
		return roomService.getAll();
	}
	
	@RequestMapping(
			value = "/getAllRooms/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRooms(@PathVariable("id") Long id) {
		List<Room> returnList = new ArrayList<Room> ();
		Hotel h = hotelRepository.findOneById(id);
		returnList = roomRepository.findAllByHotel(h);
		if (!returnList.isEmpty()) {
			for (Room r : returnList) {
				if (countAveragePricing(r)>0.0) {
					r.setPrice(countAveragePricing(r));
				}
			}
	        return new ResponseEntity<List<Room>>(returnList,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
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
	@RequestMapping(value="/deleteRoom/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<?> deleteRoom(@PathVariable("id") Long id){
		System.out.println("Dosao u delete room ");

		Room rdel = roomRepository.findOneById(id);
		try {
			roomService.deleteRoom(rdel);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}catch(Exception e ) {
			
		}
		
	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
	}
	
	@RequestMapping(value="/changeRoom/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> changeRoom(@RequestBody RoomDTO room,
										@PathVariable("id") Long id){
		System.out.println("Dosao u change service");
		Room r2 = new Room(room); 
		

		Room r = roomRepository.findOneById(id);

		if (room.getCapacity()!=null) {
			r.setCapacity(r2.getCapacity());
		}
		if  (room.getPrice()!=null) {
			r.setPrice(r2.getPrice());
		}
		if(room.getRoom_description()!= null) {
			System.out.println(r2.getRoom_description());

			r.setRoom_description(r2.getRoom_description());
		}
		if (room.getRoom_average_rating()!=null) {
			r.setRoom_average_rating(r2.getRoom_average_rating());
		}
		
		try {
			roomRepository.save(r);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	
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
			List<ReservationRoom> reservationRoom = reservationRoomService.findAllByRoom(r);
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
		//racunanjen totalne cijene
		for(Room r: returnList) {
			if (!returnList.isEmpty()) {
				Double totalPrice = countPriceInSearchRooms(r,startDate,endDate);
				System.out.println("totalna cijena je : "+ totalPrice);
				r.setTotalPrice(totalPrice);
			}
		}
		//izbacivanje soba sa popustom
		returnList = nonDiscountRooms(returnList, startDate, endDate);
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

	//@PreAuthorize("hasAuthority('getRatingRoom')")
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
		System.out.println("dosau u izracunaj prosjek");

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
		System.out.println("prosjek je: "+finalCount);

		return finalCount;

	}
	
	@RequestMapping(value="/isReserved/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean isReserved(@PathVariable("id") Long idRoom) {

		Date today=new Date();
				
		List<ReservationRoom> returnList = new ArrayList<ReservationRoom>();
		Room room = roomService.findOneById(idRoom);
		returnList = reservationRoomService.findAllByRoom(room);
		
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
	

	@RequestMapping(value="/searchFast/{idRes}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchFast(@RequestBody SearchFormServices searchForm, 
										@PathVariable("idRes") Long idRes){
		System.out.println("Dosao u search rooooom faaaaaaaaaast");
		
		FlightReservation help = new FlightReservation();
		
		help = flightRepository.findOneById(idRes);
		if (help!=null) {
			if (help.getDatum().getTime()>searchForm.getStartDate().getTime()) {
				 //return new ResponseEntity<Boolean>(true, HttpStatus.OK);
				return new ResponseEntity<>(null, HttpStatus.OK);
			}
		}
		List<DiscountHotel> povratna= new ArrayList<DiscountHotel>();
		
		//List<RentACar> all = rentalcarService.getAll();
		List<Hotel> all = hotelRepository.findAll();
		List<Hotel> returnList = new ArrayList<Hotel>();
		
		List<Hotel> returnList2 = new ArrayList<Hotel>();
		List<Hotel> returnList3 = new ArrayList<Hotel>();
		List<DiscountHotel> pomocna1= new ArrayList<DiscountHotel>();

		//ako se pretrazuje po nazivu
				if (searchForm.getNameHotel() != null) {
					System.out.println("pretragap po naz");
					for (Hotel h : all) {
						if (h.getName().equals(searchForm.getNameHotel())) {
							returnList.add(h);
						}
						returnList3 = returnList;
					}
					//ako se pretrazuje i po nazivu i po gradu
					if (searchForm.getCity() != null) {
						System.out.println("pretragap po gradu i naz");
						for (Hotel h : returnList) {
							if (h.getCity().equals(searchForm.getCity())) {
								returnList2.add(h);
							}
						}
						returnList3 = returnList2;
					}
				}
				//ako se ne pretrazuje po nazivu nego samo gradu 
				else {
					if (searchForm.getCity() != null) {
						System.out.println("pretragap po gradu samo");
						for (Hotel h : all) {
							if (h.getCity().equals(searchForm.getCity())) {
								returnList.add(h);	
							}
						}
						returnList3 = returnList;
					}
					
					//ni po nazivu ni po gradu
					returnList3 = all;
				}
		
		//pretraga po datumu
		
		if (searchForm.getStartDate()!=null && searchForm.getEndDate()!=null) {
			System.out.println("u trecoj lisri ima hotela: " +returnList3.size() );
			for (Hotel hot : returnList3) {
				 boolean free = true;
				 
				System.out.println("usao u prvu petlju : svih hotela");
				//pronalazim sve sobe hotela
				pomocna1 = dhRepository.findAllByHotel(hot);
				int reserved = pomocna1.size();
				
				if (!pomocna1.isEmpty()) {			
					for(DiscountHotel dh : pomocna1) {
						free = checkforfreeDH(dh, searchForm.getEndDate(), searchForm.getStartDate());
						if (!free) {
							povratna.add(dh);
						}
					}
				}

			}


		}
		
		return new ResponseEntity<List<DiscountHotel>>(povratna, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/getDiscountRoomsid/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DiscountHotel>>  getDiscountRoomsid(@PathVariable("id") Long id) {
		
		Hotel hotel = hotelRepository.findOneById(id);
		List<DiscountHotel> pomocni = new ArrayList<DiscountHotel>();
		
		if (hotel!=null) {
			pomocni= dhRepository.findAllByHotel(hotel);
			
		}
		return new ResponseEntity<List<DiscountHotel>>(pomocni, HttpStatus.OK);
	
	}
	
	public boolean checkforfreeDH(DiscountHotel res, Date endDate, Date startDate) {
		
		//provjeravamo datum koji smo unijeli za preuzimanje vozila
		//ako je on nakon datuma vracanja vozila koji je registrovan -ok
		if(startDate.getTime() >= res.getDateTo().getTime()) {
			return true;
			
		}
		else {
			if(endDate.getTime() <= res.getDateFrom().getTime())
			{
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public Double countAveragePricing(Room r) {
		
		Double returnValue = 0.0;
		Double suma = 0.0;
		
		List<Pricing> pomList = pricingService.findAllByRoom(r);
		if(!pomList.isEmpty()) {
			for (Pricing p : pomList) {
				suma += p.getPrice();
				System.out.println("suma " + suma);
			}
			System.out.println("djelim sa " + pomList.size());
				returnValue = suma/pomList.size();
			
		}
		System.out.println("dobio sam sa " + Double.parseDouble(new DecimalFormat("##.#").format(returnValue)));
		return Double.parseDouble(new DecimalFormat("##.#").format(returnValue));
		//return returnValue;
	}
	
	//racunanje konacne cijene za pretrazene sobe
	public Double countPriceInSearchRooms(Room r, Date startDate, Date endDate) {
		Double returnValue = 0.0;
		Double returnValue1 = 0.0;
		//System.out.println("dosao u racunam prosjecnu cijenu za search room");
		//System.out.println("start date " + startDate);
		//System.out.println("end date " + endDate);
		long brojDana = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
		System.out.println("broj dana " + brojDana);
		List<Pricing> pomList = pricingService.findAllByRoom(r);
		if(pomList!=null) {
			for (Pricing p : pomList) {
				//System.out.println("kroz jedan prajsing");
				// pronasla cjenovnik u kome se nalazi pocetni datum
				if (startDate.getTime()<=p.getDateTo().getTime() && startDate.getTime()>=p.getDateFrom().getTime()) {
					if (endDate.getTime()<=p.getDateTo().getTime() && endDate.getTime()>=p.getDateFrom().getTime()) {
						//ako se i kraj i pocetak nalaze u istom cjenovnkiku
						System.out.println("Nalaze se u istom cjenovniku");
						returnValue = brojDana * p.getPrice();
						return Double.parseDouble(new DecimalFormat("##.##").format(returnValue));
					}
					else {
						//ako se ne nalaze u isstom cjenovniku
						//racunam za prvi cjenovnik
						System.out.println(" ne Nalaze se u istom cjenovniku prvi");
						long brojDanaPrviCj = ChronoUnit.DAYS.between(startDate.toInstant(), p.getDateTo().toInstant());
						//System.out.println("broj dana 1 " + (brojDanaPrviCj+1));
						returnValue1 = (brojDanaPrviCj+1) * p.getPrice();
					//	System.out.println("cena1 "+ returnValue1);
						
					}
					
					
				}
				//racunam za drugi
				if (endDate.getTime()<=p.getDateTo().getTime() && endDate.getTime()>=p.getDateFrom().getTime()) {
					System.out.println("ne nalaze se u istom cjenovniku drugi ");
					long brojDanaDrugiCj = ChronoUnit.DAYS.between(p.getDateFrom().toInstant(), endDate.toInstant());
					//System.out.println("broj dana 2 " + (brojDanaDrugiCj));
					Double returnValue2 = (brojDanaDrugiCj )* p.getPrice();
					returnValue = returnValue1 + returnValue2;
					//System.out.println("cena2 "+ returnValue2);
				}
			}
		
		}
		System.out.println("cena "+ returnValue);
		return Double.parseDouble(new DecimalFormat("##.##").format(returnValue));
	}
	
	//Izbaci sobe koje su na popustu
	List<Room> nonDiscountRooms (List<Room> rooms, Date startDate, Date endDate){
		System.out.println("nonDiscountRooms ");
		List<Room> returnList = new ArrayList<Room>();
		returnList = rooms;
		if (!returnList.isEmpty()) {
			
				System.out.println("broj soba " + returnList.size());
			for (Room r : returnList) {
				if(returnList.size()>0) {
				System.out.println("kroz sobe ");
				List<DiscountHotel> discountRoom = dhRepository.findAllByRoom(r);
				if (!discountRoom.isEmpty()) {
					System.out.println("postoji diskaunt ");
					for(DiscountHotel d : discountRoom) {
						if (d.getDateFrom().getTime()<startDate.getTime() && d.getDateTo().getTime()>endDate.getTime()) {
							System.out.println("uklanjam sobu ");
							returnList.remove(r);
						}
					}
				}
			}
		}
		}
		return returnList;
	}

}
