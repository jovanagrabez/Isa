package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.RatingRoom;

public class RatingRoomDTO {
	
	private Long id;
	private UserDTO user;
	private RoomDTO room;
	private int rate;
	
	public RatingRoomDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	public RatingRoomDTO(Long id, UserDTO user, RoomDTO room, int rate) {
		super();
		this.id = id;
		this.user = user;
		this.room = room;
		this.rate = rate;
	}
	
	public RatingRoomDTO(RatingRoom rate) {
		this.id = rate.getId();
		this.user = new UserDTO(rate.getUser());
		this.room = new RoomDTO(rate.getRoom());
		this.rate = rate.getRate();
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public RoomDTO getRoom() {
		return room;
	}

	public void setRoom(RoomDTO room) {
		this.room = room;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
	
	
}
