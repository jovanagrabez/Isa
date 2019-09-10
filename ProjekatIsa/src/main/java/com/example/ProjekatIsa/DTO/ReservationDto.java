package com.example.ProjekatIsa.DTO;

import java.util.Date;

import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.Reservation;
import com.example.ProjekatIsa.model.ReservationRoom;

public class ReservationDto {
	
	private Long id;
    private FlightReservation flightReservation;
    private CarReservation carReservation;
    private Date dateCreated;
    private ReservationRoom roomReservation;


    public ReservationDto() {

    }

    public ReservationDto(Reservation reservation) {

        this.id = reservation.getId();
        if (reservation.getCarReservation() != null){
            if (reservation.getCarReservation().getEndDate() != null) {
                this.carReservation = reservation.getCarReservation();
            }
        }
        if (reservation.getRoomReservation() != null){
            if (reservation.getRoomReservation().getEndDate() != null) {
                this.roomReservation = reservation.getRoomReservation();
            }
        }
        this.flightReservation = reservation.getFlightReservation();
        this.dateCreated = reservation.getDateCreated();
    }

    public ReservationRoom getRoomReservation() {
        return roomReservation;
    }

    public void setRoomReservation(ReservationRoom roomReservation) {
        this.roomReservation = roomReservation;
    }

    public CarReservation getCarReservation() {
        return carReservation;
    }

    public void setCarReservation(CarReservation carReservation) {
        this.carReservation = carReservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
