package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.RatingFlight;

public class RatingFlightDTO {
	
	private Long id;
	private UserDTO user;
	private FlightDTO flight;
	private int rate;
	
	
	
	
	public RatingFlightDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RatingFlightDTO(Long id, UserDTO user, FlightDTO flight, int rate) {
		super();
		this.id = id;
		this.user = user;
		this.flight = flight;
		this.rate = rate;
	}
	
	public RatingFlightDTO(RatingFlight rate) {
		this.id = rate.getId();
		this.user = new UserDTO(rate.getUser());
		this.flight = new FlightDTO(rate.getFlight());
		this.rate = rate.getRate();
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
	public FlightDTO getFlight() {
		return flight;
	}
	public void setFlight(FlightDTO flight) {
		this.flight = flight;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
	


}
