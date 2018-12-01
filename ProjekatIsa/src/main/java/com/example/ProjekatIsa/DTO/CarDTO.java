package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Car;

public class CarDTO {
	
	private Long id;
	private int number;
	private int price;
	
	public CarDTO(Car car) {
		this(car.getId(), car.getNumber(), car.getPrice());
	}
	
	public CarDTO(Long id, int number, int price) {
		super();
		this.id = id;
		this.number = number;
		this.price = price;
	}


	public CarDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
	
	
	
	

}
