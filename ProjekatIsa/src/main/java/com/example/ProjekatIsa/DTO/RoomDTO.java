package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Room;

public class RoomDTO {

	private Long id;
	private int number;
	
	public RoomDTO() {
		
	}

	public RoomDTO(Long id, int number) {
		super();
		this.id = id;
		this.number = number;
	}

	public RoomDTO(Room room) {
		this(room.getId(), room.getNumber());
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
