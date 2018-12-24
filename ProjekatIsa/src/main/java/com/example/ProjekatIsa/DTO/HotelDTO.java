package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Hotel;

public class HotelDTO {
  
	private Long id;
    private String name;
    private String adress;
    private String description;
	private Double averageRating;
    
    public HotelDTO() {
		
	}

	public HotelDTO(Long id, String name, String adress, String description, Double averageRating) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.averageRating = averageRating;
	}

	public HotelDTO(Hotel hotel) {
		this(hotel.getId(), hotel.getName(), hotel.getAdress(), hotel.getDescription(),hotel.getAverageRating());
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

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}
 
    
}
