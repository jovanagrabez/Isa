package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Room;

public class RoomDTO {

	private Long id;
	private int number;
	private int price;
	
	public RoomDTO() {
		
	}

	public RoomDTO(Long id, int number, int price) {
		super();
		this.id = id;
		this.number = number;
		this.price = price;
	}

	public RoomDTO(Room room) {
		this(room.getId(), room.getNumber(), room.getPrice());
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
	
	
	
}
