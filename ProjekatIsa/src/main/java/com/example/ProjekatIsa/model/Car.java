package com.example.ProjekatIsa.model;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
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
	private String regnumber;
	
	@Column(name = "car_name", nullable = false, updatable = false)
	private String name;
	
	@Column(name = "car_price", nullable = false, updatable = false)
	private int price;
	
	@Column(name ="rating", nullable = false)
	private double averageRating;
	
	@Column(name="prod_year", nullable = false)
	private int prodYear;
	
	@ManyToOne
	@JoinColumn(name="rentacar_id")
	private RentACar rentalcars;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="filijale_id")
	private Filijale filijale;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="category_id")
	private Category category;
	
	@OneToMany(mappedBy="car")
    protected List<PricingCar> pricingCar;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "car_rating_car", joinColumns = @JoinColumn(name = "car_id"), inverseJoinColumns = @JoinColumn(name = "ratingCar_id"))
    private Set<RatingCar> car_ratings;

	
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

	public String getNumber() {
		return regnumber;
	}

	public void setNumber(String regnumber) {
		this.regnumber = regnumber;
	}
	


	public RentACar getRentalcars() {
		return rentalcars;
	}

	public void setRentalcars(RentACar rentalcars) {
		this.rentalcars = rentalcars;
	}

	public Car(Long id, String regnumber, int price,String name, double averageRating,int prodYear,Filijale filijala, Category category) {
		super();
		this.id = id;
		this.regnumber = regnumber;
		this.price = price;
		this.name = name;
		this.averageRating = averageRating;
		this.prodYear = prodYear;
		this.filijale = filijale;
		this.category = category;
		
	}
	
	public Car(CarDTO c) {
		setId(c.getId());
		setName(c.getName());
		setNumber(c.getNumber());
		setPrice(c.getPrice());
		
	}
	
	public Filijale getFilijale() {
		return filijale;
	}

	public void setFilijale(Filijale filijale) {
		this.filijale = filijale;
	}

	public int getProdYear() {
		return prodYear;
	}

	public void setProdYear(int prodYear) {
		this.prodYear = prodYear;
	}

	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}


}
