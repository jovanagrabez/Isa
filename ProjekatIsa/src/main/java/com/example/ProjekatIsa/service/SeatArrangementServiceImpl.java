package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.SeatArrangement;

@Service
public class SeatArrangementServiceImpl implements SeatArrangementService{

	
	    @Autowired
	    private  com.example.ProjekatIsa.repository.SeatArrangementRepository  SeatArrangementRepository;

	    @Override
	    public List< SeatArrangement> getAllSeatArrangements() {
	        return  SeatArrangementRepository.findAll();
	    }

	    @Override
	    public  SeatArrangement getSeatArrangementByName(String name) {
	        return  SeatArrangementRepository.findSeatArrangementByName(name);
	    }

	    @Override
	    public  SeatArrangement getSeatArrangementById(Long id) {
	        return  SeatArrangementRepository.findSeatArrangementById(id);
	    }

	    @Override
	    public  SeatArrangement addSeatArrangement( SeatArrangement  SeatArrangement) {
	        return  SeatArrangementRepository.save( SeatArrangement);
	    }

	    @Override
	    public void deleteSeatArrangement( SeatArrangement  SeatArrangement) {
	         SeatArrangementRepository.delete( SeatArrangement);
	    }

	    @Override
	    public  SeatArrangement updateSeatArrangement( SeatArrangement  SeatArrangement) {

	        SeatArrangement savedSeatArrangement = this. SeatArrangementRepository.findSeatArrangementById( SeatArrangement.getId());
	        savedSeatArrangement.setName( SeatArrangement.getName());
	        savedSeatArrangement.setSeatRows( SeatArrangement.getSeatRows());
	        savedSeatArrangement.setSeatColumns( SeatArrangement.getSeatColumns());

	        return  SeatArrangementRepository.save( SeatArrangement);
	    }

	    @Override
	    public  SeatArrangement saveSeatArrangement( SeatArrangement  SeatArrangement) {
	        return  SeatArrangementRepository.save( SeatArrangement);
	    }
}
