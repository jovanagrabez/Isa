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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room implements Serializable{

	private static final long serialVersionUID = 883;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "room_number", nullable = false, updatable = false)
	private int number;

	@Column(name = "room_price", nullable = false, updatable = false)
	private int price;
	
	@Column(name = "room_description", nullable = false, columnDefinition="VARCHAR(50)")
	private String room_description;
	
	@Column(name = "room_average_rating", nullable = true)
	private Double room_average_rating;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "room_rating_room", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "ratingRoom_id"))
    private Set<RatingRoom> room_ratings;
	
	public Set<RatingRoom> getRoom_ratings() {
		return room_ratings;
	}

	public void setRoom_ratings(Set<RatingRoom> room_ratings) {
		this.room_ratings = room_ratings;
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

	public Room(Long id,int number, int price, String room_description, Double room_average_rating) {
		super();
		this.id = id;
		this.number = number;
		this.price = price;
		this.room_description = room_description;
		this.room_average_rating = room_average_rating;
	}

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
