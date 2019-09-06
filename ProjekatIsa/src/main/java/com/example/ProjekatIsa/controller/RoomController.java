package com.example.ProjekatIsa.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.HotelRepository;
import com.example.ProjekatIsa.repository.ReservationRoomRepository;
import com.example.ProjekatIsa.repository.RoomRepository;
import com.example.ProjekatIsa.repository.UserRepository;
import com.example.ProjekatIsa.service.HotelService;
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
	private UserRepository userRepository;
	
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
			List<ReservationRoom> reservationRoom = r.getReservationRoom();
			boolean free = true;
			
			int reserved = 0;
			
			for(ReservationRoom res : reservationRoom) {
				
				free = checkforfree(res, endDate, startDate);
				if (!free) {
					reserved ++;
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
	
	@RequestMapping(value="/bookRoom",
			method = RequestMethod.POST)
	ResponseEntity<CarReservationDTO> bookRoom(@RequestBody ReservationRoom roomRes){
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
			
			reservationRoomRepository.save(returnValue);
			return new ResponseEntity<>(HttpStatus.CREATED);
			
		}else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
}
