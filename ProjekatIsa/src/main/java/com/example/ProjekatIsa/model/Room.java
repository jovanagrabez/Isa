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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.example.ProjekatIsa.DTO.RoomDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "room")
public class Room implements Serializable{

	private static final long serialVersionUID = 883;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "room_number", nullable = false)
	private int number;

	@Column(name = "room_price", nullable = false)
	private int price;
	
	@Column(name = "capacity", nullable = false)
	private Double capacity;
	
	@Column(name = "room_description", nullable = false, columnDefinition="VARCHAR(50)")
	private String room_description;
	
	@Column(name = "room_average_rating", nullable = true)
	private Double room_average_rating;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	@JsonIgnore
	@OneToMany(mappedBy="room")
    protected List<ReservationRoom> reservationRoom;
	
	@JsonIgnore
	@OneToMany(mappedBy="room")
    protected List<Pricing> pricing;
	
	//@JsonIgnore
	//@OneToMany(mappedBy="room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OneToMany(mappedBy="room", fetch = FetchType.LAZY)
    private Set<RatingRoom> ratings;
	
	public Set<RatingRoom> getRoom_ratings() {
		return ratings;
	}

	public void setRoom_ratings(Set<RatingRoom> room_ratings) {
		this.ratings = room_ratings;
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

	public String getRoom_description() {
		return room_description;
	}

	public void setRoom_description(String room_description) {
		this.room_description = room_description;
	}

	public Double getRoom_average_rating() {
		return room_average_rating;
	}

	public void setRoom_average_rating(Double room_average_rating) {
		this.room_average_rating = room_average_rating;
	}

	public Room(Long id,int number, int price, String room_description, Double room_average_rating,Set<RatingRoom> ratings) {
		super();
		this.id = id;
		this.number = number;
		this.price = price;
		this.room_description = room_description;
		this.room_average_rating = room_average_rating;
		this.ratings = ratings;
	}

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Room(RoomDTO r) {
		setId(r.getId());
		setNumber(r.getNumber());
		setPrice(r.getPrice());
		setRoom_description(r.getRoom_description());
		setRoom_average_rating(r.getRoom_average_rating());
		setCapacity(r.getCapacity());
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Pricing> getPricing() {
		return pricing;
	}

	public void setPricing(List<Pricing> pricing) {
		this.pricing = pricing;
	}

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public List<ReservationRoom> getReservationRoom() {
		return reservationRoom;
	}

	public void setReservationRoom(List<ReservationRoom> reservationRoom) {
		this.reservationRoom = reservationRoom;
	}
	
	
}
