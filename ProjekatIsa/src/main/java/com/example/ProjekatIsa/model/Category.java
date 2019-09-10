package com.example.ProjekatIsa.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name="mark", nullable = false)
	private String mark; //A, B, C, D
	
	@Column(name="description", nullable = false)
	private String description;
	
	@Column(name="seats", nullable = false)
	private int seats;
	
	@Column(name="price", nullable = false)
	private double price;
	
	@JsonIgnore
	@OneToMany(mappedBy="category",fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Car> cars;
	
	
	public Category(){
		
	}
	
	public Category(Long id, String mark, String description, int seats, List<Car> cars) {
		super();
		this.id = id;
		this.mark = mark;
		this.description = description;
		this.seats = seats;
		this.cars = cars;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	
	
	
	

}
