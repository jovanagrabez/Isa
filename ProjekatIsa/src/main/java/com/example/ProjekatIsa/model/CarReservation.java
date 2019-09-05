package com.example.ProjekatIsa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="car_reservation")
public class CarReservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", nullable = false, updatable = false)
	private Long id; 
    
	@Column(name = "start_date", nullable=false)
    protected Date startDate;
    
	@Column(name = "end_date", nullable=false)
    protected Date endDate;
	
	@Column(name="pickup_place", nullable = true)
	private String pickupPlace;
	
	@Column(name="return_place", nullable = true)
	private String returnPlace;
	
	@Column(name="category", nullable = false)
	private String category;
	
	@Column(name="num_people", nullable= true)
	private int numPeople;
	
	@Column(name="num_days",nullable = false)
	private int numDays;
	
	@Column(name="total_price", nullable = false)
	private double totalPrice;
	
	@Column(name = "day_rez")
	private Date dayRez;
    
    
    @ManyToOne( fetch = FetchType.LAZY)
    protected User user;
    
    @ManyToOne( fetch = FetchType.LAZY)
	private Car car;
	

	@Column(name="flag", nullable = false)
	private boolean flag;
	
	


	public CarReservation() {
		
	}


	public CarReservation(Long id, Date startDate, Date endDate, String pickupPlace,
			String returnPlace, String category, int numPeople, int numDays, double totalPrice, Date dayRez, User user,
			Car car, boolean flag) {
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
		this.user = user;
		this.car = car;
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


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Date getDayRez() {
		return dayRez;
	}


	public void setDayRez(Date dayRez) {
		this.dayRez = dayRez;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}


	public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	

}
