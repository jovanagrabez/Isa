package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Seat;

@Service
public interface SeatService {
	 
	
	List<Seat> getAllSeats();
	Seat getSeatById(Long id);
	Seat addSeat(Seat seat);
	void deleteSeat(Seat seat);
	Seat updateSeat(Seat seat);

}
