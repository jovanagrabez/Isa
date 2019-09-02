package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Car;

public class CarDTO {
	
	private Long id;
	private String name;
	private int number;
	private int price;
	
	public CarDTO(Car car) {
		this(car.getId(), car.getName(), car.getNumber(), car.getPrice());
	}
	
	public CarDTO(Long id, String name, int number, int price) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.price = price;
	}


	public CarDTO() {
		
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
