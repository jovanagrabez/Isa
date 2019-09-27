package com.example.ProjekatIsa.DTO;

import java.util.Date;

public class PricingCarDTO {
	
	 private Long id;
	 private Double price;
	 private Date dateFrom;
	 private Date dateTo;
	 
	 
	 
	 
	public PricingCarDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PricingCarDTO(Long id, Double price, Date dateFrom, Date dateTo) {
		super();
		this.id = id;
		this.price = price;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	 
	 
	 

}
