package com.example.ProjekatIsa.DTO;

public class RatingRentACarServiceDTO {
	
	private Long rentacarId;
	private int value;
	
	
	
	public RatingRentACarServiceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public RatingRentACarServiceDTO(Long rentacarId, int value) {
		super();
		this.rentacarId = rentacarId;
		this.value = value;
	}



	public Long getRentacarId() {
		return rentacarId;
	}



	public void setRentacarId(Long rentacarId) {
		this.rentacarId = rentacarId;
	}



	public int getValue() {
		return value;
	}



	public void setValue(int value) {
		this.value = value;
	}
	
	
	
	

}
