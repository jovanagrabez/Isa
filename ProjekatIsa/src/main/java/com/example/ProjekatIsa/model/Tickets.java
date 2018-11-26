package com.example.ProjekatIsa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "aviocompany")
public class Tickets {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false, updatable = false)
    private Long id;
	
	
	@Column(name = "price", nullable = false, updatable = false)
	private int price;
    
	
	@Column(name = "luggage", nullable = false, updatable = false)
	private boolean luggage;
	
	
	@Column(name = "lugg_price", nullable = false, updatable = false)
	private int lugg_price;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public boolean isLuggage() {
		return luggage;
	}


	public void setLuggage(boolean luggage) {
		this.luggage = luggage;
	}


	public int getLugg_price() {
		return lugg_price;
	}


	public void setLugg_price(int lugg_price) {
		this.lugg_price = lugg_price;
	}


	public Tickets(Long id, int price, boolean luggage, int lugg_price) {
		super();
		this.id = id;
		this.price = price;
		this.luggage = luggage;
		this.lugg_price = lugg_price;
	}


	public Tickets() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
	
	
	
	
}
