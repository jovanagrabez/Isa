package com.example.ProjekatIsa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	public Room(Long id,int number, int price) {
		super();
		this.id = id;
		this.number = number;
		this.price = price;
	}

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
