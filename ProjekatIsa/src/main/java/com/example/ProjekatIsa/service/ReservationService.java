package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.DTO.ReservationDto;
import com.example.ProjekatIsa.model.Reservation;

@Service
public interface ReservationService {
	
	List<Reservation> getReservationsByUser(String username);
    Reservation createReservation(ReservationDto reservation);
    List<Reservation> findAll();
    void cancelReservation(Long id);
    List<ReservationDto> getAllByAirlineId(Long id);
    void sendReservationEmail(Long id) throws InterruptedException;

}
