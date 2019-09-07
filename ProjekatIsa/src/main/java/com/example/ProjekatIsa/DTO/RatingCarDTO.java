package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.RatingCar;
import com.example.ProjekatIsa.model.RatingRentACar;

public class RatingCarDTO {
	
	private Long id;
	private UserDTO user;
	private CarDTO car;
	private int rate;
	
	
	
	
	public RatingCarDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RatingCarDTO(Long id, UserDTO user, CarDTO car, int rate) {
		super();
		this.id = id;
		this.user = user;
		this.car = car;
		this.rate = rate;
	}
	
	
	public RatingCarDTO(RatingCar rate) {
		this.id = rate.getId();
		this.user = new UserDTO(rate.getUser());
		this.car = new CarDTO(rate.getCar());
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
	public CarDTO getCar() {
		return car;
	}
	public void setCar(CarDTO car) {
		this.car = car;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
	
	
	

}
