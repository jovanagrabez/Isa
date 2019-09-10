package com.example.ProjekatIsa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.ProjekatIsa.DTO.FlightDestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Table
@Entity
public class FlightDest implements Serializable{

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "flight_destination_id")
	    private Long id;

	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    private Flight flight;

	    @ManyToOne(fetch = FetchType.LAZY)
	    private Destination destinations;

	    @Column
	    private String description;         //ovo ce nam trebati da znam tacno koja je destinacija za polazak, dolazak

	    public FlightDest() {}

	    public FlightDest(FlightDestDTO flightDestinationDto){
	        id = flightDestinationDto.getId();
	        description = flightDestinationDto.getDescription();
	    }

	    public FlightDest(Flight flight, Destination destinations) {
	        this.flight = flight;
	        this.destinations = destinations;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Flight getFlight() {
	        return flight;
	    }

	    public void setFlight(Flight flight) {
	        this.flight = flight;
	    }

	    public Destination getDestination() {
	        return destinations;
	    }

	    public void setDestination(Destination destinations) {
	        this.destinations = destinations;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }
	
	
}
