package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Tickets;

public class TicketsDTO {

	 private Long id;
	 private int price;
	 private boolean luggage;
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
	public TicketsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TicketsDTO(Long id, int price, boolean luggage, int lugg_price) {
		super();
		this.id = id;
		this.price = price;
		this.luggage = luggage;
		this.lugg_price = lugg_price;
	}
	 
	 
	 public TicketsDTO(Tickets t) {
		 this(t.getId(),t.getPrice(),t.isLuggage(),t.getLugg_price());
	 }
	
	
}
