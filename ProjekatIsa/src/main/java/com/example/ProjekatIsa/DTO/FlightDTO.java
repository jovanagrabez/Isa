package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Flight;

public class FlightDTO {
	
	
	 private Long id;
	 private String take_off;
	 private String landing;
	 private String time;
	 private String travel_time;
	 private int number;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTake_off() {
		return take_off;
	}
	public void setTake_off(String take_off) {
		this.take_off = take_off;
	}
	public String getLanding() {
		return landing;
	}
	public void setLanding(String landing) {
		this.landing = landing;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTravel_time() {
		return travel_time;
	}
	public void setTravel_time(String travel_time) {
		this.travel_time = travel_time;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public FlightDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FlightDTO(Long id, String take_off, String landing, String time, String travel_time, int number) {
		super();
		this.id = id;
		this.take_off = take_off;
		this.landing = landing;
		this.time = time;
		this.travel_time = travel_time;
		this.number = number;
	}
	 
	 
	 public FlightDTO(Flight f) {
		 this(f.getId(),f.getTake_off(),f.getLanding(),f.getTime(),f.getTravel_time(),f.getNumber());
	 }






}
