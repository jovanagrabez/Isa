package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Category;

public class CategoryDTO {
	private Long id;
	private String mark;
	private String description;
	private int seats;
	private double price;
	
	
	
	
	public CategoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CategoryDTO(Category c) {
		this.id = c.getId();
		this.mark = c.getMark();
		this.description = c.getDescription();
		this.seats = c.getSeats();
		this.price = c.getPrice();
	}
	
	public CategoryDTO(Long id, String mark, String description, int seats, double price) {
		super();
		this.id = id;
		this.mark = mark;
		this.description = description;
		this.seats = seats;
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	

}
