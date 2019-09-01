package com.example.ProjekatIsa.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "flight")
public class Flight implements Serializable {
	//PREPRAVITI DATE TIPE KASNIJE KOD STRINGOVA takeoff,landing, time
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "take_off", nullable = false, columnDefinition="VARCHAR(40)")

	private String take_off;
	
	@Column(name = "landing", nullable = false, columnDefinition="VARCHAR(40)")
    private String landing;
	
	@Column(name = "time", nullable = false, columnDefinition="VARCHAR(40)")
    private String time;
	
	@Column(name = "travel_time", nullable = false, columnDefinition="VARCHAR(40)")
    private String travel_time;
	
	@Column(name = "number", nullable = true, columnDefinition="INT(2)")
    private int number;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "flight_destination", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "destination_id"))   
	private Set<Destination> destination;
	
	@Column(name = "seat", nullable = true, columnDefinition="BOOLEAN")
    private boolean seat;
	
	/* @JsonManagedReference
	 @ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	 private Aviocompany aviocompany;*/
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "flight_price", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "ticket_id"))   
	private Set<Tickets> price;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTake_off() {
		return take_off;
	}


	public void setTake_off(String take_off) {
		this.take_off = take_off;
	}


	public String getLanding() {
		return landing;
	}


	public void setLanding(String landing) {
		this.landing = landing;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getTravel_time() {
		return travel_time;
	}


	public void setTravel_time(String travel_time) {
		this.travel_time = travel_time;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public Set<Destination> getDestination() {
		return destination;
	}


	public void setDestination(Set<Destination> destination) {
		this.destination = destination;
	}


	public boolean isSeat() {
		return seat;
	}


	public void setSeat(boolean seat) {
		this.seat = seat;
	}


	public Set<Tickets> getPrice() {
		return price;
	}


	public void setPrice(Set<Tickets> price) {
		this.price = price;
	}


	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Flight(Long id, String take_off, String landing, String time, String travel_time, int number,
			Set<Destination> destination, boolean seat, Set<Tickets> price) {
		super();
		this.id = id;
		this.take_off = take_off;
		this.landing = landing;
		this.time = time;
		this.travel_time = travel_time;
		this.number = number;
		this.destination = destination;
		this.seat = seat;
		this.price = price;
	}
	
	
	

}
