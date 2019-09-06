package com.example.ProjekatIsa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.ProjekatIsa.DTO.AdditionalServiceForHotelDTO;

@Entity
@Table(name = "additional_service_hotel")
public class AdditionalServiceForHotel implements Serializable{

	private static final long serialVersionUID = 65757;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "additional_service_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "name", nullable = false, columnDefinition="VARCHAR(40)")
    private String name;
	
	@Column(name = "price", nullable = false)
    private Double price;
	
	@ManyToOne
	@JoinColumn(name="hotel_id")
	private Hotel hotel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AdditionalServiceForHotel(Long id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public AdditionalServiceForHotel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AdditionalServiceForHotel(AdditionalServiceForHotelDTO a) {
		setId(a.getId());
		setPrice(a.getPrice());
		setName(a.getName());
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	
}
