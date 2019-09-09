package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.FlightDest;

@Service
public interface FlightDestService {

    List<FlightDest> getAllFlightDestinations();
    FlightDest getFlightDestinationById(Long id);
    FlightDest addFlightDestination(FlightDest flightDestnation);
    void deleteFlightDestnation(FlightDest flightDestnation);
    FlightDest updateFlightDestination(FlightDest flightDestnation);
    void deleteAndAddNewFlightDestinationsByFlight(Long flightId, List<FlightDest> flightDestinations);

}
