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
@Table(name = "rating_rentacarservice")
public class RatingRentACar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingcar_id", nullable = false, updatable = false)
    private Long id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="rentacar_id")
	private RentACar rentacar;
	
	@Column(name="rate", nullable = false)
	private int rate;
	
	
	

	public RatingRentACar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RatingRentACar(Long id, User user, RentACar car, int rate) {
		super();
		this.id = id;
		this.user = user;
		this.rentacar = car;
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

	public RentACar getCar() {
		return rentacar;
	}

	public void setCar(RentACar car) {
		this.rentacar = car;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	
	
	
	

}
