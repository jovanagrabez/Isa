package com.example.ProjekatIsa.service;

import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.Passenger;

@Service
public interface FlightReservationService {
	
	FlightReservation createFlightReservation(FlightReservation flightReservation);
    Passenger getPassengerSeatById(Long id);
    void deleteInvite(Long id);

}
