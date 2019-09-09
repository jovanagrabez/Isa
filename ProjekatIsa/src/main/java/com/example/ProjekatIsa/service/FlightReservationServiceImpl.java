package com.example.ProjekatIsa.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.Passenger;
import com.example.ProjekatIsa.model.Seat;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.FlightReservationRepository;
import com.example.ProjekatIsa.repository.PassengerRepository;
import com.example.ProjekatIsa.repository.UserRepository;

@Service
public class FlightReservationServiceImpl implements FlightReservationService {
	
	 @Autowired
	    private FlightReservationRepository flightReservationRepository;

	    @Autowired
	    private PassengerRepository passengerOnFlightSeatRepository;

	 
	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private SeatService seatService;



	    @Override
	    public FlightReservation createFlightReservation(FlightReservation flightReservation) {

	        FlightReservation reservation = new FlightReservation();
	        reservation.setFlightId(flightReservation.getFlightId());
	        reservation.setUserId(flightReservation.getUserId());
	        reservation.setDatum(new Date());

	        for (Passenger passengerOnFlightSeat : flightReservation.getPassengersOnSeats()) {
	            passengerOnFlightSeat.getSeat().setState("taken");
	            Seat seat = this.seatService.updateSeat(passengerOnFlightSeat.getSeat());
	            passengerOnFlightSeat.setSeat(seat);
	            passengerOnFlightSeat = this.passengerOnFlightSeatRepository.save(passengerOnFlightSeat);
	            reservation.getPassengersOnSeats().add(passengerOnFlightSeat);

	            if (passengerOnFlightSeat.getPassengerId() != 0 &&                  // ako je 0 nema nalog
	                    passengerOnFlightSeat.getPassengerId() != flightReservation.getUserId()) {
	                User user = this.userRepository.findOneById(passengerOnFlightSeat.getPassengerId());
	            }
	        }

	        reservation = this.flightReservationRepository.save(reservation);

	        return reservation;
	    }

	    @Override
	    public Passenger getPassengerSeatById(Long id) {

	        Passenger pass = this.passengerOnFlightSeatRepository.findPassangerById(id);
	        return pass;
	    }

	    @Override
	    public void deleteInvite(Long id) {
	        this.passengerOnFlightSeatRepository.deleteById(id);
	    }


	 


}
