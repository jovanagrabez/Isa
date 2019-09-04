package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Car;

public class CarDTO {
	
	private Long id;
	private String name;
	private String regnumber;
	private int price;
	private int prodYear;
	
	public CarDTO(Car car) {
		this(car.getId(), car.getName(), car.getNumber(), car.getPrice(),car.getProdYear());
	}
	
	public CarDTO(Long id, String name, String regnumber, int price,int prodYear) {
		super();
		this.id = id;
		this.name = name;
		this.regnumber = regnumber;
		this.price = price;
		this.prodYear = prodYear;
	}


	public CarDTO() {
		
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

	public String getRegnumber() {
		return regnumber;
	}

	public void setRegnumber(String regnumber) {
		this.regnumber = regnumber;
	}

	public int getProdYear() {
		return prodYear;
	}

	public void setProdYear(int prodYear) {
		this.prodYear = prodYear;
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

	public String getNumber() {
		return regnumber;
	}

	public void setNumber(String regnumber) {
		this.regnumber = regnumber;
	}
	
	
	
	
	
	
	

}
