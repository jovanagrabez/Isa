package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.RatingHotel;

public class RatingHotelDTO {

	private Long id;
	private UserDTO user;
	private HotelDTO hotel;
	private int rate;
	
	
	
	
	
	public RatingHotelDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RatingHotelDTO(RatingHotel hotel) {
		this.id = hotel.getId();
		this.user = new UserDTO(hotel.getUser());
		this.hotel = new HotelDTO(hotel.getHotel());
		this.rate = rate;
		
	}
	
	public RatingHotelDTO(Long id, UserDTO user, HotelDTO hotel, int rate) {
		super();
		this.id = id;
		this.user = user;
		this.hotel = hotel;
		this.rate = rate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public HotelDTO getHotel() {
		return hotel;
	}
	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
	
}
