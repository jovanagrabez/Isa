package com.example.ProjekatIsa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.ProjekatIsa.DTO.RentACarDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "rentacar")
public class RentACar implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rentacar_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "name", unique = true, columnDefinition="VARCHAR(40)")
    private String name;
	
	@Column(name = "city", nullable = false, columnDefinition="VARCHAR(40)")
    private String city;
	
	@Column(name = "adress", columnDefinition="VARCHAR(100)")
    private String adress;
	
	@Column(name = "description", columnDefinition="VARCHAR(50)")
    private String description;
	
	@Column(name = "average_rating", nullable = true)
	private Double average_rating;
	
	@JsonIgnore
	@OneToMany(mappedBy="rentacar",fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Car> car;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "car_price", joinColumns = @JoinColumn(name = "rentacar_id"), inverseJoinColumns = @JoinColumn(name = "car_id"))   
	private Set<Car> price;
	
	@JsonIgnore
	@OneToMany(mappedBy = "rentacar",fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Filijale> filijale;
	
	//@JsonIgnore
	@OneToMany(mappedBy="rentacar")    
	private List<RatingRentACar> service_ratings;   
	
	
	
	
	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Car> getCar() {
		return car;
	}

	public void setCar(List<Car> car) {
		this.car = car;
	}

	public Set<Car> getPrice() {
		return price;
	}

	public void setPrice(Set<Car> price) {
		this.price = price;
	}

	public List<Filijale> getFilijale() {
		return filijale;
	}

	public void setFilijale(List<Filijale> filijale) {
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
	
	public void addFilijale(Filijale f) {
		if(this.filijale==null) {
			this.filijale = new ArrayList<Filijale>();
		}
		this.filijale.add(f);
	}

	public RentACar(Long id, String name, String city, String adress, String description, Double average_rating) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.average_rating = average_rating;
		this.city = city;
	}
	
	public RentACar(RentACarDTO r) {
		setId(r.getId());
		setName(r.getName());
		setDescription(r.getDescription());
		setAdress(r.getAdress());
		setCity(r.getCity());
		
	}

	public RentACar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addCar(Car car) {
		// TODO Auto-generated method stub
		if(this.car!=null) {
			this.car = new ArrayList<Car>();
		}
		
		this.car.add(car);
		
	}
	
	
	
	

}
