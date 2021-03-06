package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Reservation;
import com.example.ProjekatIsa.model.User;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation, Long>{
	
	List<Reservation> findReservationsByUser(User user);
    List<Reservation> findAll();
    Reservation findReservationById(Long id);

}
