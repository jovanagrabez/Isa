package DTO;

public class RatingRoomDTO {
	
	private Long roomID;
	private int value;
	
	public RatingRoomDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RatingRoomDTO(Long roomID, int value) {
		super();
		this.roomID = roomID;
		this.value = value;
	}
	public Long getRoomID() {
		return roomID;
	}
	public void setRoomID(Long roomID) {
		this.roomID = roomID;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
}
