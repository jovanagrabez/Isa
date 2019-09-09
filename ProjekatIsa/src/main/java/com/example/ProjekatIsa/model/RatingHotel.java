package com.example.ProjekatIsa.model;

import java.io.Serializable;

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
@Table(name = "rating_hotel")
public class RatingHotel implements Serializable {

	private static final long serialVersionUID = 996;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratinghotel_id", nullable = false, updatable = false)
    private Long id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	@Column(name="rate", nullable = false)
	private int rate;
	
	
	

	public RatingHotel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RatingHotel(Long id, User user, Hotel hotel, int rate) {
		super();
		this.id = id;
		this.user = user;
		this.hotel = hotel;
		this.rate = rate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
	
	
	
}
