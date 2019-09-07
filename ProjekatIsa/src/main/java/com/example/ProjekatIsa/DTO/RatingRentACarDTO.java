package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.RatingRentACar;

public class RatingRentACarDTO {
	
	private Long id;
	private UserDTO user;
	private RentACarDTO car;
	private int rate;
	
	
	
	public RatingRentACarDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RatingRentACarDTO(RatingRentACar servis) {
		this.id = servis.getId();
		this.user = new UserDTO(servis.getUser());
		this.car = new RentACarDTO(servis.getCar());
		this.rate = servis.getRate();
		
	}



	public RatingRentACarDTO(Long id, UserDTO user, RentACarDTO car, int rate) {
		super();
		this.id = id;
		this.user = user;
		this.car = car;
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



	public RentACarDTO getCar() {
		return car;
	}



	public void setCar(RentACarDTO car) {
		this.car = car;
	}



	public int getRate() {
		return rate;
	}



	public void setRate(int rate) {
		this.rate = rate;
	}
	
	




	
	
	
	

}
