package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Hotel;

public class HotelDTO {
  
	private Long id;
    private String name;
    private String adress;
    private String description;
	private Double average_rating;
    
    public HotelDTO() {
		
	}

	public HotelDTO(Long id, String name, String adress, String description, Double average_rating) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.average_rating = average_rating;
	}

	public HotelDTO(Hotel hotel) {
		this(hotel.getId(), hotel.getName(), hotel.getAdress(), hotel.getDescription(),hotel.getAverage_rating());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAdress() {
		return adress;
	}

	public String getDescription() {
		return description;
	}

	public Double getAverage_rating() {
		return average_rating;
	}

	public void setAverage_rating(Double average_rating) {
		this.average_rating = average_rating;
	}
	
	
 
    
}
