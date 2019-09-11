package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.RatingCar;
import com.example.ProjekatIsa.model.RatingFlight;
import com.example.ProjekatIsa.model.User;

@Repository
public interface RatingFlightRepository extends JpaRepository<RatingFlight, Long> {

	List<RatingFlight> findAllByUser(User user);
	List<RatingFlight> findAllByFlight(Flight f);

}
