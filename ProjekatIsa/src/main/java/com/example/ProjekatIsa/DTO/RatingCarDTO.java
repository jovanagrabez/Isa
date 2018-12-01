package com.example.ProjekatIsa.DTO;

public class RatingCarDTO {
	
	private Long carId;
	private int value;
	
	
	public RatingCarDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RatingCarDTO(Long carId, int value) {
		super();
		this.carId = carId;
		this.value = value;
	}


	public Long getCarId() {
		return carId;
	}


	public void setCarId(Long carId) {
		this.carId = carId;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}
	
	
	
	

}
