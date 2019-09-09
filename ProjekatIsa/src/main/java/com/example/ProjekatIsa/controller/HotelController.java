package com.example.ProjekatIsa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
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
import com.example.ProjekatIsa.DTO.ReservationRoomDTO;
import com.example.ProjekatIsa.DTO.RoomDTO;
import com.example.ProjekatIsa.model.AdditionalServiceForHotel;
import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.RatingHotel;
import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.SearchFormHotel;
import com.example.ProjekatIsa.repository.AdditionalServiceForHotelRepository;
import com.example.ProjekatIsa.repository.HotelRepository;
import com.example.ProjekatIsa.repository.RatingHotelRepository;
import com.example.ProjekatIsa.repository.ReservationRoomRepository;
import com.example.ProjekatIsa.repository.RoomRepository;
import com.example.ProjekatIsa.service.AdditionalServiceForHotelService;
import com.example.ProjekatIsa.service.HotelService;
import com.example.ProjekatIsa.service.ReservationRoomService;
import com.example.ProjekatIsa.service.RoomService;

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
	private RoomService roomService;
	
	@Autowired
	private AdditionalServiceForHotelRepository addRepository;
	
	@Autowired
	private AdditionalServiceForHotelService addService;
	
	@Autowired 
	private ReservationRoomRepository reservationRoomRepository;
		
	@Autowired
	private RatingHotelRepository ratingHotelRepository;
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
	public ResponseEntity<?> deleteService(@PathVariable("id") Long id){
		System.out.println("Dosao u delete service");
		
		//Hotel h = hotelRepository.findOneById(id);
		AdditionalServiceForHotel a = addRepository.findOneById(id);
		
		try {
			addService.deleteAdditionalServiceForHotel(a);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}catch(Exception e ) {
			
		}
		
	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
	}
	
	@PreAuthorize("hasAuthority('deleteRoom')")
	@RequestMapping(value="/deleteRoom/{id}",
			method = RequestMethod.POST)
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
	
	@PreAuthorize("hasAuthority('changeService')")
	@RequestMapping(value="/changeService/{id}",
			method = RequestMethod.POST)
	public ResponseEntity<?> changeService(@RequestBody AdditionalServiceForHotelDTO add,
										@PathVariable("id") Long id){
		System.out.println("Dosao u change service");
		
		AdditionalServiceForHotel a = addRepository.findOneById(add.getId());
		
		
		if (add.getName()!=null) {
			a.setName(add.getName());
		}
		if (add.getPrice()!=null) {
			a.setPrice(add.getPrice());
		}
		
		try {
			addRepository.save(a);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	
	}
	
	@PreAuthorize("hasAuthority('changeRoom')")
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
		if  (room.getPrice() > 0) {
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
	
	//pretraga
	@RequestMapping(value="/searchHotels",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchHotels(@RequestBody SearchFormHotel searchForm){
		System.out.println("Dosao u add search hotel");
		
		System.out.println(searchForm.getName() + " :ime" + searchForm.getCity() + " :grad"
				+ searchForm.getStartDate() + " : start date" + searchForm.getEndDate() + " :endDate");
		
		List<Hotel> allHotels = hotelService.getAll();
		List<Hotel> returnList = new ArrayList<Hotel>();
		List<Hotel> returnList2 = new ArrayList<Hotel>();
		List<Hotel> returnList3 = new ArrayList<Hotel>();

		//pretraga po datumu
		
		if (searchForm.getStartDate()!=null && searchForm.getEndDate()!=null) {
			for (Hotel hot : allHotels) {
				System.out.println("usao u prvu petlju : svih hotela");
				System.out.println("hotel ima soba: " + hot.getRooms().size());
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
			returnList3 = hotelService.getAll();
		}

		//ako se pretrazuje po nazivu
		if (searchForm.getName() != null) {
			for (Hotel h : returnList3) {
				if (h.getName().contains(searchForm.getName())) {
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
				return new ResponseEntity<List<Hotel>>(returnList2, HttpStatus.OK);
			}
			//ako se pretrazuje samo po nazivu
			else {
				return new ResponseEntity<List<Hotel>>(returnList, HttpStatus.OK);
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
				return new ResponseEntity<List<Hotel>>(returnList, HttpStatus.OK);
			}
			//ako ne pretrazuje ni po nazivu ni po gradu
			return new ResponseEntity<List<Hotel>>(allHotels, HttpStatus.OK);
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
	
	@PreAuthorize("hasAuthority('lastWeekReservations')")
	@RequestMapping(value="/getLastWeekReservations/{id}/{dateToday}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReservationRoom>>  getLastWeekReservations(@PathVariable("id") Long idHotela, @PathVariable("dateToday") String od) {
		List<ReservationRoom> returnList = new ArrayList<ReservationRoom>();
		List<ReservationRoom> pomList = new ArrayList<ReservationRoom>();
		
		Date today=new Date();
		long ltime=today.getTime()-7*24*60*60*1000;
		Date today_7=new Date(ltime);
		System.out.println("pocetak: " + today_7);
		
		Hotel hotel = hotelRepository.findOneById(idHotela);
		List<Room> rooms = roomRepository.findAllByHotel(hotel);
		List<ReservationRoom> resrooms = new ArrayList<ReservationRoom>();
		
		System.out.println("broj soba " + rooms.size());
		if (!rooms.isEmpty()) {
			for (Room r : rooms) {
				resrooms = reservationRoomRepository.findAllByRoom(r);
				System.out.println("broj rezervacija bilo kakvih " + resrooms.size());
				//proba
				if (!resrooms.isEmpty()) {
				for (ReservationRoom rrr : resrooms) {
					System.out.println("proba  " + rrr.getStartDate());
					if(today.getTime() >= rrr.getStartDate().getTime() && today_7.getTime()<= rrr.getStartDate().getTime())
					{
						System.out.println("pronsasao1  " + rrr.getStartDate());
						returnList.add(rrr);
					} 
					}
				}	
			}
		}
	
		return new ResponseEntity<List<ReservationRoom>>(returnList,HttpStatus.OK);
	}
	@PreAuthorize("hasAuthority('getAllReservations')")
	@RequestMapping(value="/getAllReservations/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReservationRoom>>  getAllReservations(@PathVariable("id") Long idHotela) {
		List<ReservationRoom> returnList = new ArrayList<ReservationRoom>();
		List<ReservationRoom> resrooms = new ArrayList<ReservationRoom>();

		Hotel hotel = hotelRepository.findOneById(idHotela);
		List<Room> rooms = roomRepository.findAllByHotel(hotel);
		System.out.println("broj soba " + rooms.size());
		if (!rooms.isEmpty()) {
			for (Room r : rooms) {
				resrooms = reservationRoomRepository.findAllByRoom(r);
				System.out.println("broj rezervacija bilo kakvih " + resrooms.size());
				//proba
				if (!resrooms.isEmpty()) {
					for (ReservationRoom rrr : resrooms) {
						returnList.add(rrr);
					}
				}
			}
		}

		return new ResponseEntity<List<ReservationRoom>>(returnList,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('getAllRatingsHotel')")
	@RequestMapping(value="/getAllRatingsHotel/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RatingHotel>>  getAllRatingsHotel(@PathVariable("id") Long idHotela) {
		List<RatingHotel> returnList = new ArrayList<RatingHotel>();
		Hotel hotel = hotelRepository.findOneById(idHotela);
		
		returnList = ratingHotelRepository.findAllByHotel(hotel);
		
		return new ResponseEntity<List<RatingHotel>>(returnList,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('getHotelRevenue')")
	@RequestMapping(value="/getHotelRevenue/{idHotela}/{od}/{Do}",
					method = RequestMethod.GET)
	public double getHotelRevenue(@PathVariable Long idHotela,@PathVariable String od,@PathVariable String Do){
		List<ReservationRoom> resrooms = new ArrayList<ReservationRoom>();

		double suma = 0;
		Date dOd = null;
		Date dDo = null;
		java.sql.Date sqlOD = null;
		java.sql.Date sqlDO = null;
		java.sql.Date sqlstart = null;
		try {
			dOd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(od);
			dDo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(Do);
			sqlOD = new java.sql.Date(dOd.getTime());
			sqlDO = new java.sql.Date(dDo.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("od od " + dOd.toString() + dDo.toString());
		System.out.println("od od sql " + sqlOD.toString() + sqlDO.toString());
		Hotel hotel = hotelRepository.findOneById(idHotela);
		List<Room> rooms = roomRepository.findAllByHotel(hotel);
		System.out.println("broj soba " + rooms.size());
		if (!rooms.isEmpty()) {
			for (Room r : rooms) {
				resrooms = reservationRoomRepository.findAllByRoom(r);

				System.out.println("broj rezervacija bilo kakvih " + resrooms.size());
				
				if (!resrooms.isEmpty()) {
				for (ReservationRoom rrr : resrooms) {
					sqlstart = new java.sql.Date(rrr.getStartDate().getTime());
					System.out.println("proba  " + sqlstart);
					if(sqlDO.getTime() >= sqlstart.getTime() && sqlOD.getTime()<= sqlstart.getTime())
					{
						System.out.println("pronsasao1  " + rrr.getStartDate());
						suma+=rrr.getTotalPrice();
					} 
					}
				}	
			}
		}
		
		return suma;
	
	}
	
	@RequestMapping(value="/sortForm/{param}",
			method = RequestMethod.POST)
	public ResponseEntity<?> sortForm(@PathVariable("param") String param, @RequestBody List<HotelDTO> servisi){
		System.out.println("usao u sortiranjee");
		
		List<HotelDTO> sorted = new ArrayList<HotelDTO>();
		
		for(HotelDTO acc : servisi) {
			System.out.println("naziv hotela je" + acc.getName());
		}
		
		String[] paramArray = param.split("=");
		String item = paramArray[0];
		String order = paramArray[1];
		boolean descending=false; 
		boolean ascending=false; 
		
		if(order.equals("descending")) {
			descending = true;
		}
		
		
		if(item.equals("adress") && order.equals("ascending") ) {
			
			sorted = servisi.stream()
					  .sorted(Comparator.comparing(HotelDTO::getAddress))
					  .collect(Collectors.toList());
			
			System.out.println("Adresa" + sorted);
		}
		
		
        if(item.equals("adress") && order.equals("descending") ) {
			
			sorted = servisi.stream()
					  .sorted(Comparator.comparing(HotelDTO::getAddress).reversed())
					  .collect(Collectors.toList());
			
			System.out.println("Adresa" + sorted);
		}
        
        
        if(item.equals("name") && order.equals("ascending") ) {
			
			sorted = servisi.stream()
					  .sorted(Comparator.comparing(HotelDTO::getName))
					  .collect(Collectors.toList());
			
			System.out.println("Name" + sorted);
		}
        
        if(item.equals("name") && order.equals("ascending") ) {
			
			sorted = servisi.stream()
					  .sorted(Comparator.comparing(HotelDTO::getName))
					  .collect(Collectors.toList());
			
			System.out.println("Name" + sorted);
		}
        
        if(item.equals("name") && order.equals("descending") ) {
			
			sorted = servisi.stream()
					  .sorted(Comparator.comparing(HotelDTO::getName).reversed())
					  .collect(Collectors.toList());
			
			System.out.println("Adresa" + sorted);
		}
        
        
        if(item.equals("rate") && order.equals("ascending") ) {
			
			sorted = servisi.stream()
					  .sorted(Comparator.comparing(HotelDTO::getAverage_rating))
					  .collect(Collectors.toList());
			
			System.out.println("Name" + sorted);
		}
        
        if(item.equals("rate") && order.equals("descending") ) {
			
			sorted = servisi.stream()
					  .sorted(Comparator.comparing(HotelDTO::getAverage_rating).reversed())
					  .collect(Collectors.toList());
			
			System.out.println("Adresa" + sorted);
		}
		
	
		return  new ResponseEntity<List<HotelDTO>>(sorted, HttpStatus.OK);
		
	}
}


