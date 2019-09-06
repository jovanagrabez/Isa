package com.example.ProjekatIsa.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProjekatIsa.model.Seat;
import com.example.ProjekatIsa.repository.SeatRepository;

@Service
public class SeatServiceImpl implements SeatService {

	
	    @Autowired
	    private SeatRepository seatRepository;

	    @Override
	    public List<Seat> getAllSeats() {
	        return seatRepository.findAll();
	    }

	    @Override
	    public Seat getSeatById(Long id) {
	        return seatRepository.findSeatById(id);
	    }

	    @Override
	    public Seat addSeat(Seat seat) {
	        return seatRepository.save(seat);
	    }

	    @Override
	    public void deleteSeat(Seat seat) {
	        seatRepository.delete(seat);
	    }

	    @Override
	    @Transactional(isolation = Isolation.SERIALIZABLE)
	    public Seat updateSeat(Seat seat) {

	        return this.seatRepository.save(seat);
	    }


}
