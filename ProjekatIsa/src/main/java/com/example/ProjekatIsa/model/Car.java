package com.example.ProjekatIsa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.ProjekatIsa.DTO.CarDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "car")

public class Car implements Serializable {
	
	private static final long serialVersionUID = 8889;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "car_number", nullable = false, updatable = false)
	private int number;
	
	@Column(name = "car_name", nullable = false, updatable = false)
	private String name;
	
	@Column(name = "car_price", nullable = false, updatable = false)
	private int price;
	
	@ManyToOne
	@JoinColumn(name="rent_cars")
	private RentACar rentalcars;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Filijale filijale;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	


	public RentACar getRentalcars() {
		return rentalcars;
	}

	public void setRentalcars(RentACar rentalcars) {
		this.rentalcars = rentalcars;
	}

	public Car(Long id, int number, int price,String name) {
		super();
		this.id = id;
		this.number = number;
		this.price = price;
		this.name = name;
		
	}
	
	public Car(CarDTO c) {
		setId(c.getId());
		setName(c.getName());
		setNumber(c.getNumber());
		setPrice(c.getPrice());
		
	}
	
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}


}
