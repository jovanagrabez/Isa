package com.example.ProjekatIsa.DTO;

public class RatingHotelDTO {

	private Long hotelID;
	private int value;
	
	public RatingHotelDTO(Long hotelID, int value) {
		super();
		this.hotelID = hotelID;
		this.value = value;
	}
	public RatingHotelDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getHotelID() {
		return hotelID;
	}
	public void setHotelID(Long hotelID) {
		this.hotelID = hotelID;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
}
