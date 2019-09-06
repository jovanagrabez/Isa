package com.example.ProjekatIsa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.core.SerializableString;


@Table
@Entity
public class Passenger  implements Serializable{
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "passenger_on_flight_seat_id")
	    private Long id;

	    @Column
	    private Long passengerId;

	    @Column
	    private String passengerName;

	    @Column
	    private String passengerLastName;

	    @Column
	    private String passengerPassport;

	    @ManyToOne
	    private Seat seat;

	    public Passenger() {}

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Long getPassengerId() {
	        return passengerId;
	    }

	    public void setPassengerId(Long passengerId) {
	        this.passengerId = passengerId;
	    }

	    public String getPassengerName() {
	        return passengerName;
	    }

	    public void setPassengerName(String passengerName) {
	        this.passengerName = passengerName;
	    }

	    public String getPassengerLastName() {
	        return passengerLastName;
	    }

	    public void setPassengerLastName(String passengerLastName) {
	        this.passengerLastName = passengerLastName;
	    }

	    public String getPassengerPassport() {
	        return passengerPassport;
	    }

	    public void setPassengerPassport(String passengerPassport) {
	        this.passengerPassport = passengerPassport;
	    }

	    public Seat getSeat() {
	        return seat;
	    }

	    public void setSeat(Seat seat) {
	        this.seat = seat;
	    }

}
