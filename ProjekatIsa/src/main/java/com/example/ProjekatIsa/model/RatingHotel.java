package com.example.ProjekatIsa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Column(name = "ratingHotel_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "value", nullable = false)
    private int value;
	
	@ManyToOne
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public RatingHotel(int value) {
		super();
		this.value = value;
	}

	public RatingHotel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
