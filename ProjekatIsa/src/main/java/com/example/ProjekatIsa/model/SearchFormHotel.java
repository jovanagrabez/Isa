package com.example.ProjekatIsa.model;

import java.util.Date;

public class SearchFormHotel {

	String name;
	String city;
	Date startDate;
	Date endDate;
	
	
	public SearchFormHotel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchFormHotel(String name, String city, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	
	
	
}
