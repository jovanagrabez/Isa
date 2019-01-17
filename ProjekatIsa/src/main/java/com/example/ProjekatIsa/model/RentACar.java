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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "rentalcars")
public class RentACar implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rentacar_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "name", nullable = false, unique = true, columnDefinition="VARCHAR(40)")
    private String name;
	
	@Column(name = "adress", nullable = false, columnDefinition="VARCHAR(100)")
    private String adress;
	
	@Column(name = "description", nullable = false, columnDefinition="VARCHAR(50)")
    private String description;
	
	@Column(name = "average_rating", nullable = true)
	private Double average_rating;
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "cars", joinColumns = @JoinColumn(name = "rentacar_id"), inverseJoinColumns = @JoinColumn(name = "car_id"))   
	private Set<Car> car;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "car_price", joinColumns = @JoinColumn(name = "rentacar_id"), inverseJoinColumns = @JoinColumn(name = "car_id"))   
	private Set<Car> price;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "filijale", joinColumns = @JoinColumn(name = "rentacar_id"), inverseJoinColumns = @JoinColumn(name = "filijale_id"))   
	private Set<Car> filijale;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "rent_rating_car", joinColumns = @JoinColumn(name = "rentacar_id"), inverseJoinColumns = @JoinColumn(name = "car_id"))
    private Set<RatingRentACar> rentacarRatings;
   
	
	
	
	
	

	public Set<Car> getCar() {
		return car;
	}

	public void setCar(Set<Car> car) {
		this.car = car;
	}

	public Set<Car> getPrice() {
		return price;
	}

	public void setPrice(Set<Car> price) {
		this.price = price;
	}

	public Set<Car> getFilijale() {
		return filijale;
	}

	public void setFilijale(Set<Car> filijale) {
		this.filijale = filijale;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public Double getAverage_rating() {
		return average_rating;
	}

	public void setAverage_rating(Double average_rating) {
		this.average_rating = average_rating;
	}

	public RentACar(Long id, String name, String adress, String description, Double average_rating) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.average_rating = average_rating;
	}

	public RentACar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
