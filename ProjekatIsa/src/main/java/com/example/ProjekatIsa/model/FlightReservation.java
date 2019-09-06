package com.example.ProjekatIsa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table
@Entity
public class FlightReservation implements Serializable{
	
	
	     @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "flight_reservation_id")
	    private Long id;

	    @Column
	    private Long flightId;

	    @Column
	    private Long userId;                    // onaj koji je napravio rezervaciju

	    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, orphanRemoval = true)
	    @JoinColumn(name = "flight_reservation", referencedColumnName = "flight_reservation_id")
	    private Set<Passenger> passengersOnSeats;

	    public FlightReservation() {

	        passengersOnSeats = new HashSet<>();
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Long getFlightId() {
	        return flightId;
	    }

	    public void setFlightId(Long flightId) {
	        this.flightId = flightId;
	    }

	    public Long getUserId() {
	        return userId;
	    }

	    public void setUserId(Long userId) {
	        this.userId = userId;
	    }

	    public Set<Passenger> getPassengersOnSeats() {
	        return passengersOnSeats;
	    }

	    public void setPassengersOnSeats(Set<Passenger> passengersOnSeats) {
	        this.passengersOnSeats = passengersOnSeats;
	    }

}
