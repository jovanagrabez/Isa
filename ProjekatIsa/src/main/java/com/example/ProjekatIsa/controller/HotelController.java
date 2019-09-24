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
import com.example.ProjekatIsa.model.RatingRoom;
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

	@RequestMapping(value="/addHotel",
			method = RequestMethod.POST)
	public ResponseEntity<?> addNewHotel(@RequestBody HotelDTO hotel){
		System.out.println("Dosao u add hotel");
		
		 Hotel h = this.hotelService.addHotel(new Hotel(hotel));
		
	        return new ResponseEntity<Hotel>(h,HttpStatus.OK);
		
	}
	
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
	
	@RequestMapping(
			value="/deleteHotel",
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
				if (!rooms.isEmpty()) {
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
					if (h.getCity().contains(searchForm.getCity())) {
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
					if (h.getCity().contains(searchForm.getCity())) {
						returnList.add(h);
					}
				}
				return new ResponseEntity<List<Hotel>>(returnList, HttpStatus.OK);
			}
			//ako ne pretrazuje ni po nazivu ni po gradu
			return new ResponseEntity<List<Hotel>>(returnList3, HttpStatus.OK);
		}
		
	
	
	}
	public boolean reserved(Room r, Date startDate, Date endDate) {
		
		List<ReservationRoom> resRoom = reservationRoomRepository.findAllByRoom(r);
		
		if(!resRoom.isEmpty()) {
			for(ReservationRoom reservation : resRoom) {
				
				if(endDate.getTime() >= reservation.getStartDate().getTime() && endDate.getTime()<= reservation.getEndDate().getTime())
				{
					return true;
				} else if(startDate.getTime() >= reservation.getStartDate().getTime() && startDate.getTime() <= reservation.getEndDate().getTime())
				{
					return true;
				}
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
	
	@RequestMapping(value="/countAverageRating/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Double  countAverageRating(@PathVariable("id") Long idHotela) {
		Double finalCount = 0.0;
		int sum = 0;
		List<RatingHotel> returnList = new ArrayList<RatingHotel>();
		Hotel hotel = hotelRepository.findOneById(idHotela);
		
		returnList = ratingHotelRepository.findAllByHotel(hotel);
		for (RatingHotel rr:returnList) {
			sum += rr.getRate();
		}
		if (!returnList.isEmpty()) {
			finalCount = (double) (sum/returnList.size());
		}
		return finalCount;
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


