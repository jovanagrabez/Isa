package com.example.ProjekatIsa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="discount_hotel")
public class DiscountHotel {

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
	
	@Column(name = "price")
	private Double price;
	
	@ManyToOne
	private Hotel hotel;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Room room;

	
	
	public DiscountHotel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiscountHotel(Long id, Date dateFrom, Date dateTo, Double discountprice, Double price, Hotel hotel,
			Room room) {
		super();
		this.id = id;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.discountprice = discountprice;
		this.price = price;
		this.hotel = hotel;
		this.room = room;
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

	public Double getDiscountprice() {
		return discountprice;
	}

	public void setDiscountprice(Double discountprice) {
		this.discountprice = discountprice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	
}
