package com.example.ProjekatIsa.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.Passenger;
import com.example.ProjekatIsa.model.Seat;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.FlightReservationRepository;
import com.example.ProjekatIsa.repository.PassengerRepository;
import com.example.ProjekatIsa.repository.SeatRepository;
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
	    
	    @Autowired
	    private SeatRepository seatRepository;
	    
	    
	    @Autowired
	    private JavaMailSender javaMailSender;

	    @Autowired
	    private Environment env;



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
	            	 try {
	            	User user = this.userRepository.findOneById(passengerOnFlightSeat.getPassengerId());
	                sendInvitationMail(user, passengerOnFlightSeat.getId());
	                
	            	 } catch (InterruptedException e) {
	                     e.printStackTrace();
	                 }
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
	    	Passenger p = this.passengerOnFlightSeatRepository.getOne(id);
	    	Seat s = this.seatService.getSeatById(p.getSeat().getId());
	    	s.setState("free");
	    	
	    	this.seatRepository.save(s);
	        this.passengerOnFlightSeatRepository.deleteById(id);
	    }


	    private void sendInvitationMail(User user, Long id) throws MailException, InterruptedException {


	        System.out.println("Sending email...");

	        SimpleMailMessage mail = new SimpleMailMessage();
	        mail.setTo(user.getEmail());
	        mail.setFrom(env.getProperty("spring.mail.username"));
	        mail.setSubject("Prijateljska pozivnica za let");

	        String url="http://localhost:4200/accept/" + id;
	        mail.setText("Kliknite ovdje da odgovorite:" + " " + url);
	        javaMailSender.send(mail);

	        System.out.println("Email sent!");

	    }
	 


}
