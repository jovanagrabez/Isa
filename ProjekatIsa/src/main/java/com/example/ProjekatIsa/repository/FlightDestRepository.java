package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.FlightDest;

@Repository
public interface FlightDestRepository extends JpaRepository<FlightDest, Long>{
	
	FlightDest findFlightDestinationById(Long id);
    List<FlightDest> findFlightDestinationsByFlight(Flight flight);

}
