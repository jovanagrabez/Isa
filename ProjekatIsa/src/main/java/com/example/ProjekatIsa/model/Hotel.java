package com.example.ProjekatIsa.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.ProjekatIsa.DTO.AviocompanyDTO;
import com.example.ProjekatIsa.DTO.HotelDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "hotel")
public class Hotel implements Serializable{

	private static final long serialVersionUID = 225;

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id", nullable = false, updatable = false)
    private Long id;

	@Column(name = "name", nullable = false, columnDefinition="VARCHAR(40)")
    private String name;
	
	@Column(name = "city", nullable = false, columnDefinition="VARCHAR(40)")
    private String city;
	
	@Column(name = "adress", nullable = false, columnDefinition="VARCHAR(100)")
    private String address;
	
	@Column(name = "description", nullable = false, columnDefinition="VARCHAR(50)")
    private String description;
	
	@Column(name = "average_rating", nullable = true)
	private Double average_rating;
	
	//@JsonIgnore
	//@OneToMany(mappedBy="hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OneToMany(mappedBy="hotel", orphanRemoval = true, cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<Room> rooms;
	
	@JsonIgnore
	@OneToMany(mappedBy="hotel")
	private List<AdditionalServiceForHotel> additional_services;

	@JsonIgnore
	@OneToMany(mappedBy="hotel")    
	private List<RatingHotel> hotel_ratings;
    

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public List<AdditionalServiceForHotel> getAdditional_services() {
		return additional_services;
	}

	public void setAdditional_services(List<AdditionalServiceForHotel> additional_services) {
		this.additional_services = additional_services;
	}

	public List<RatingHotel> getHotel_ratings() {
		return hotel_ratings;
	}

	public void setHotel_ratings(List<RatingHotel> hotel_ratings) {
		this.hotel_ratings = hotel_ratings;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public void addAdditionalService(AdditionalServiceForHotel a) {
		if (this.additional_services==null) {
			this.additional_services = new ArrayList<AdditionalServiceForHotel>();
		}
			this.additional_services.add(a);		
	}
	public void addRoom(Room r) {
		if (this.rooms==null) {
			this.rooms = new ArrayList<Room>();
		}
			
		this.rooms.add(r);		
	}
	public void removeAdditionalService(AdditionalServiceForHotel a) {
			this.additional_services.remove(a);		
	}
	public void removeRoom(Room r) {
		this.rooms.remove(r);		
	}
	public Hotel() {
		super();
	}

	public Hotel(Long id, String name,String city, String address, String description, Double average_rating) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.description = description;
		this.average_rating = average_rating;
	}
	public Hotel(HotelDTO h) {
		 setId(h.getId());
	     setName(h.getName());
	     setDescription(h.getDescription());
	     setAddress(h.getAddress());
	     setCity(h.getCity());
		
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
}
