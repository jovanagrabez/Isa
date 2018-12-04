package com.example.ProjekatIsa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rating_car")
public class RatingCar implements Serializable {
	private static final long serialVersionUID = 99524; 
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingCar_id", nullable = false, updatable = false)
    private Long id;
	
	
	//klijent
	
	@Column(name = "value", nullable = false)
    private int value;

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

	public RatingCar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RatingCar(Long id, int value) {
		super();
		this.id = id;
		this.value = value;
	}
	
	

}
