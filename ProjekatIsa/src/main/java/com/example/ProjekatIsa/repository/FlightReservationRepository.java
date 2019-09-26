package com.example.ProjekatIsa.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.User;

@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {

	FlightReservation findOneById(Long id);
	
	FlightReservation findFlightReservationById(Long id);

	List<FlightReservation> findAllByUserId(Long id);

	FlightReservation findByFlightId(Long flight_id);
	
	Set<FlightReservation> findAllByFlightId(Long id);
	
	
	
}
