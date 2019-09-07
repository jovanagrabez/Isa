package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Room;

public class RoomDTO {

	private Long id;
	private int number;
	private int price;
	private Double capacity;
	private String room_description;
	private Double room_average_rating;
	
	public RoomDTO() {
		
	}

	public RoomDTO(Long id, int number, int price,Double capacity, String room_description, Double room_average_rating) {
		super();
		this.id = id;
		this.number = number;
		this.price = price;
		this.room_description = room_description;
		this.room_average_rating = room_average_rating;
		this.capacity = capacity;
		
	}
	
	public RoomDTO(Room room) {
		this(room.getId(),room.getNumber(),room.getPrice(),room.getRoom_average_rating(),room.getRoom_description(),room.getCapacity());
	}

	
	
	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getRoom_description() {
		return room_description;
	}

	public void setRoom_description(String room_description) {
		this.room_description = room_description;
	}

	public Double getRoom_average_rating() {
		return room_average_rating;
	}

	public void setRoom_average_rating(Double room_average_rating) {
		this.room_average_rating = room_average_rating;
	}
	
	
	
}
