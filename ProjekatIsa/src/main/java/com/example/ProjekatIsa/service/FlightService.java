package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.DTO.FlightDTO;
import com.example.ProjekatIsa.model.Flight;
@Service
public interface FlightService {
	
	List<Flight> getAllFlights();
    Flight getFlightById(Long id);
    Flight addFlight(Flight flight);
    Flight updateFlight(Flight flight);
    Flight saveFlight(Flight flight);
    boolean deleteFlight(Flight flight);
    Flight updateSeats(Flight fligh);

}
