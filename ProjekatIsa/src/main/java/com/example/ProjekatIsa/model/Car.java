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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@Entity
@Table(name = "car")

public class Car implements Serializable {
	
	private static final long serialVersionUID = 8889;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "car_number", nullable = false)
	private String car_number;
	
	@Column(name = "car_name", nullable = false)
	private String name;
	
	@Column(name = "price", nullable = false)
	private int price;
	
	@Column(name ="average_rating")
	private Double average_rating;
	
	@Column(name="prod_year", nullable = false)
	private int prod_year;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="rentacar_id")
	private RentACar rentacar;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="filijale_id")
	private Filijale filijale;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="category_id")
	private Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy="car")
    protected List<PricingCar> pricingCar;
	
//	@JsonIgnore
//	@OneToMany(mappedBy="car", fetch = FetchType.LAZY)
//    private Set<RatingCar> ratings;

	@JsonIgnore
	@OneToMany(mappedBy="car")
	private List<CarReservation> reservation;
	
	
//	@JsonIgnore
//	@OneToMany(mappedBy="car")
//	private List<Discount> discount;
	
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name="discount_id")
	private Discount discount;
	
//	@ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "car_discount",
//            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "car_id"),
//            inverseJoinColumns = @JoinColumn(name = "discount_id", referencedColumnName = "discount_id"))
//	private Set<Discount> discount;
	
	
	
	
	public List<CarReservation> getReservation() {
		return reservation;
	}




	public void setReservation(List<CarReservation> reservation) {
		this.reservation = reservation;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getCar_number() {
		return car_number;
	}




	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}




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




	public Double getAverage_rating() {
		return average_rating;
	}




	public void setAverage_rating(Double average_rating) {
		this.average_rating = average_rating;
	}




	public int getProd_year() {
		return prod_year;
	}




	public void setProd_year(int prod_year) {
		this.prod_year = prod_year;
	}




	public RentACar getRentalcars() {
		return rentacar;
	}




	public void setRentalcars(RentACar rentacar) {
		this.rentacar = rentacar;
	}




	public Filijale getFilijale() {
		return filijale;
	}




	public void setFilijale(Filijale filijale) {
		this.filijale = filijale;
	}




	public Category getCategory() {
		return category;
	}




	public void setCategory(Category category) {
		this.category = category;
	}




	public List<PricingCar> getPricingCar() {
		return pricingCar;
	}




	public void setPricingCar(List<PricingCar> pricingCar) {
		this.pricingCar = pricingCar;
	}




//	public Set<RatingCar> getRatings() {
//		return ratings;
//	}
//
//
//
//
//	public void setRatings(Set<RatingCar> ratings) {
//		this.ratings = ratings;
//	}
//	
	


	public Car(Long id, String car_number, String name, int price, Double average_rating, int prod_year,
			RentACar rentacar, Filijale filijale, Category category, List<PricingCar> pricingCar,
			Set<RatingCar> ratings, List<CarReservation> reservation) {
		super();
		this.id = id;
		this.car_number = car_number;
		this.name = name;
		this.price = price;
		this.average_rating = average_rating;
		this.prod_year = prod_year;
		this.rentacar = rentacar;
		this.filijale = filijale;
		this.category = category;
		this.pricingCar = pricingCar;
		//this.ratings = ratings;
		this.reservation = reservation;
	}
	
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Car(CarDTO c) {
		setId(c.getId());
		setName(c.getName());
		setCar_number(c.getCar_number());
		setPrice(c.getPrice());
		setProd_year(c.getProd_year());
		setAverage_rating(c.getAverage_rating());
		//setCategory(c.getCategory());
	}
	
	
	
	


}
