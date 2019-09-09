package com.example.ProjekatIsa.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="discount")
public class Discount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "date_from", nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	@Temporal(TemporalType.DATE)
	private Date dateFrom;
	
	@Column(name = "date_to", nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	@Temporal(TemporalType.DATE)
	private Date dateTo;
	
	@Column(name = "discount_price", nullable = false)
	private Double discountprice;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Car car;
	
	@Column(name = "price")
	private Double price;
	
	
	
	@ManyToOne
	@JsonIgnore
	private RentACar rentACar;
	
	
	
	public Discount() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	

	

	public Discount(Long id, Date dateFrom, Date dateTo, Car car, Double price, Double discount) {
		super();
		this.id = id;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.car = car;
		this.price = price;
		this.discountprice = discount;
	}
	
	








	public Double getDiscountprice() {
		return discountprice;
	}








	public void setDiscountprice(Double discountprice) {
		this.discountprice = discountprice;
	}








	public RentACar getRentACar() {
		return rentACar;
	}








	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}








	public Car getCar() {
		return car;
	}








	public void setCar(Car car) {
		this.car = car;
	}








	public Double getPrice() {
		return price;
	}








	public void setPrice(Double price) {
		this.price = price;
	}








	public void setDiscount(Double discount) {
		this.discountprice = discount;
	}








	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
