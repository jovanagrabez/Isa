package com.example.ProjekatIsa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Table
@Entity
public class Reservation implements Serializable {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

   
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private CarReservation carReservation;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private FlightReservation flightReservation;

    @Column
    private Date dateCreated;


    @OneToOne
    private ReservationRoom roomReservation;

    public Reservation() {
    }

    public ReservationRoom getRoomReservation() {
        return roomReservation;
    }

    public void setRoomReservation(ReservationRoom roomReservation) {
        this.roomReservation = roomReservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CarReservation getCarReservation() {
        return carReservation;
    }

    public void setCarReservation(CarReservation carReservation) {
        this.carReservation = carReservation;
    }

    public FlightReservation getFlightReservation() {
        return flightReservation;
    }

    public void setFlightReservation(FlightReservation flightReservation) {
        this.flightReservation = flightReservation;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

}
