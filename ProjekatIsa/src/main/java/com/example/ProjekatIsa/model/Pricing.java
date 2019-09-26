package com.example.ProjekatIsa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.ProjekatIsa.DTO.PricingDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pricing")
public class Pricing implements Serializable{
	private static final long serialVersionUID = 225;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pricing_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "price", nullable = false)
    private Double price;
	
	@Column(name = "date_from", nullable = false)
    private Date dateFrom;
	
	@Column(name = "date_to", nullable = false)
    private Date dateTo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Pricing() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Pricing(PricingDTO p) {
		setId(p.getId());
		setDateFrom(p.getDateFrom());
		setDateTo(p.getDateTo());
		setPrice(p.getPrice());
		
	}
}
