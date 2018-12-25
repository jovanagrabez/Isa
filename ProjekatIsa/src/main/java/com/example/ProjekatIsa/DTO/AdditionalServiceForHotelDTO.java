package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.AdditionalServiceForHotel;

public class AdditionalServiceForHotelDTO {

    private Long id;
    private String name;
    private Double price;

    public AdditionalServiceForHotelDTO(Long id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public AdditionalServiceForHotelDTO(AdditionalServiceForHotel a) {
		this(a.getId(), a.getName(),a.getPrice());
	}
	
	public AdditionalServiceForHotelDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

    
    
}
