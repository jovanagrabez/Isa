package com.example.ProjekatIsa.DTO;

import java.util.Date;

import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.User;

public class ReservationRoomDTO {
	private Long id; 
	private Date startDate;
	private Date endDate;
	private Double totalPrice;
	private String category;
	private Long numPeople;
	private String reservationStatus;
	private String reservationRating;
	private UserDTO user;
	private RoomDTO room;
	
	
	
	public ReservationRoomDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservationRoomDTO(Long id, Date startDate, Date endDate, Double totalPrice, String category, Long numPeople,
			String reservationStatus, String reservationRating, UserDTO user, RoomDTO room) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalPrice = totalPrice;
		this.category = category;
		this.numPeople = numPeople;
		this.reservationStatus = reservationStatus;
		this.reservationRating = reservationRating;
		this.user = user;
		this.room = room;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getNumPeople() {
		return numPeople;
	}
	public void setNumPeople(Long numPeople) {
		this.numPeople = numPeople;
	}
	public String getReservationStatus() {
		return reservationStatus;
	}
	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	public String getReservationRating() {
		return reservationRating;
	}
	public void setReservationRating(String reservationRating) {
		this.reservationRating = reservationRating;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public RoomDTO getRoom() {
		return room;
	}
	public void setRoom(RoomDTO room) {
		this.room = room;
	}
	
	
}



