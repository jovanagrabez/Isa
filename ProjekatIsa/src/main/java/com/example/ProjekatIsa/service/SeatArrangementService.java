package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.SeatArrangement;

@Service
public interface SeatArrangementService {

	List<SeatArrangement> getAllSeatArrangements();
    SeatArrangement getSeatArrangementByName(String name);
    SeatArrangement getSeatArrangementById(Long id);
    SeatArrangement addSeatArrangement(SeatArrangement SeatArrangement);
    void deleteSeatArrangement(SeatArrangement SeatArrangement);
    SeatArrangement updateSeatArrangement(SeatArrangement SeatArrangement);
    SeatArrangement saveSeatArrangement(SeatArrangement SeatArrangement);
}
