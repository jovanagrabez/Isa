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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "flight")
public class Flight implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "take_off", nullable = false)

	private Date take_off;
	
	@Column(name = "landing", nullable = false)
    private Date landing;
	
	@Column(name = "time", nullable = false, columnDefinition="VARCHAR(40)")
    private String time;
	
	@Column(name = "travel_time", nullable = false)
    private double travel_time;
	
	@Column(name = "number", nullable = true, columnDefinition="INT(2)")
    private int number;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "flight_destination", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "destination_id"))   
	private Set<Destination> destination;
	
	
	
	/* @JsonManagedReference
	 @ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	 private Aviocompany aviocompany;
	*/

	@Column(name = "average_rating", nullable = false, columnDefinition="DOUBLE")
	 private double average_rating;

	@Column(name = "number_of_rating", nullable = false, columnDefinition="DOUBLE")
	private double number_of_rating;

	@Column(name = "sum_rating", nullable = false, columnDefinition="DOUBLE")
	private double sum_rating;
	
	/*@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "flight_price", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "ticket_id"))   
	private Set<Tickets> price;
*/
	
	@Column(name = "economy_price", nullable = false, columnDefinition="DOUBLE")
	 private double economy_price;

	@Column(name = "premium_economy_price", nullable = false, columnDefinition="DOUBLE")
	 private double premium_economy_price;

	@Column(name = "business_price", nullable = false, columnDefinition="DOUBLE")
     private double business_price;

	@Column(name = "first_price", nullable = false, columnDefinition="DOUBLE")
	 private double first_price;

	@Column(name = "distance")
	 private int distance;  
	
	@Column(name = "baggage_description", nullable = true, columnDefinition="VARCHAR(40)")
     private String baggage_description;


	@OneToOne
    private SeatArrangement seatArrangement;
	 
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},fetch=FetchType.LAZY, orphanRemoval = true)
	private Set<Seat> seats;
	 
	public double getAverageRating() {
		return average_rating;
	}


	public void setAverageRating(double average_rating) {
		this.average_rating = average_rating;
	}


	public SeatArrangement getSeatArrangement() {
		return seatArrangement;
	}


	public void setSeatArrangement(SeatArrangement seatArrangement) {
		this.seatArrangement = seatArrangement;
	}


	public Set<Seat> getSeats() {
		return seats;
	}


	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}


	public double getNumberOfRating() {
		return number_of_rating;
	}


	public void setNumberOfRating(double number_of_rating) {
		this.number_of_rating = number_of_rating;
	}


	public double getSumRating() {
		return sum_rating;
	}


	public void setSumRating(double sum_rating) {
		this.sum_rating = sum_rating;
	}


	public double getEconomyPrice() {
		return economy_price;
	}


	public void setEconomyPrice(double economy_price) {
		this.economy_price = economy_price;
	}


	public double getPremiumEconomyPrice() {
		return premium_economy_price;
	}


	public void setPremiumEconomyPrice(double premium_economy_price) {
		this.premium_economy_price = premium_economy_price;
	}


	public double getBusinessPrice() {
		return business_price;
	}


	public void setBusinessPrice(double business_price) {
		this.business_price = business_price;
	}


	public double getFirstPrice() {
		return first_price;
	}


	public void setFirstPrice(double first_price) {
		this.first_price = first_price;
	}


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	public String getBaggageDescription() {
		return baggage_description;
	}


	public void setBaggageDescription(String baggage_description) {
		this.baggage_description = baggage_description;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getTake_off() {
		return take_off;
	}


	public void setTake_off(Date take_off) {
		this.take_off = take_off;
	}


	public Date getLanding() {
		return landing;
	}


	public void setLanding(Date landing) {
		this.landing = landing;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public double getTravel_time() {
		return travel_time;
	}


	public void setTravel_time(double travel_time) {
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

	

/*	public Set<Tickets> getPrice() {
		return price;
	}


	public void setPrice(Set<Tickets> price) {
		this.price = price;
	}

*/
	

	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Flight(Long id, Date take_off, Date landing, String time, double travel_time, int number,
		Set<Seat>	seats,SeatArrangement seatArrangement,  double average_rating, double number_of_rating, double sum_rating,
		Set<Destination> destination, double economy_price, double premium_economy_price, double business_price,
			double first_price, int distance, String baggage_description) {
		super();
		this.id = id;
		this.take_off = take_off;
		this.landing = landing;
		this.time = time;
		this.travel_time = travel_time;
		this.number = number;
		this.destination = destination;
		this.seats = seats;
		this.seatArrangement=seatArrangement;
		this.average_rating = average_rating;
		this.number_of_rating = number_of_rating;
		this.sum_rating = sum_rating;
	//	this.price = price;
		this.economy_price = economy_price;
		this.premium_economy_price = premium_economy_price;
		this.business_price = business_price;
		this.first_price = first_price;
		this.distance = distance;
		this.baggage_description = baggage_description;
	}


	
	
	

}
