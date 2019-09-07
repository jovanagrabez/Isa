package com.example.ProjekatIsa.DTO;

import java.util.Date;

import com.example.ProjekatIsa.model.CarReservation;

public class CarReservationDTO {
	
	private Long id;
	private Date startDate;
	private Date endDate;
	private String pickupPlace;
	private String returnPlace;
	private String category;
	private int numPeople;
	private int numDays;
	private Double totalPrice;
	private Date dayRez;
	
	private CarDTO car;
	private UserDTO user;
	private boolean flag;
	
	
	
	public CarReservationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CarReservationDTO(CarReservation c) {
		this.id = c.getId();
		this.startDate = c.getStartDate();
		this.endDate = c.getEndDate();
		this.pickupPlace = c.getPickupPlace();
		this.returnPlace = c.getReturnPlace();
		this.category = c.getCategory();
		this.numPeople = c.getNumPeople();
		this.numDays = c.getNumDays();
		this.totalPrice = c.getTotalPrice();
		this.dayRez = c.getDayRez();
		this.user = new UserDTO(c.getUser());
		this.car = new CarDTO(c.getCar());
		this.flag = c.isFlag();
				
	}
	public CarReservationDTO(Long id, Date startDate, Date endDate, String pickupPlace, String returnPlace,
			String category, int numPeople, int numDays, Double totalPrice, Date dayRez, CarDTO car, UserDTO user,
			boolean flag) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pickupPlace = pickupPlace;
		this.returnPlace = returnPlace;
		this.category = category;
		this.numPeople = numPeople;
		this.numDays = numDays;
		this.totalPrice = totalPrice;
		this.dayRez = dayRez;
		this.car = car;
		this.user = user;
		this.flag = flag;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getPickupPlace() {
		return pickupPlace;
	}
	public void setPickupPlace(String pickupPlace) {
		this.pickupPlace = pickupPlace;
	}
	public String getReturnPlace() {
		return returnPlace;
	}
	public void setReturnPlace(String returnPlace) {
		this.returnPlace = returnPlace;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getNumPeople() {
		return numPeople;
	}
	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}
	public int getNumDays() {
		return numDays;
	}
	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getDayRez() {
		return dayRez;
	}
	public void setDayRez(Date dayRez) {
		this.dayRez = dayRez;
	}
	public CarDTO getCar() {
		return car;
	}
	public void setCar(CarDTO car) {
		this.car = car;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	
	

}
