package com.example.ProjekatIsa.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.DTO.ReservationDto;
import com.example.ProjekatIsa.model.Destination;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.Passenger;
import com.example.ProjekatIsa.model.Reservation;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.FlightRepository;
import com.example.ProjekatIsa.repository.FlightReservationRepository;
import com.example.ProjekatIsa.repository.ReservationRepository;
import com.example.ProjekatIsa.repository.SeatRepository;
import com.example.ProjekatIsa.repository.UserRepository;
import com.example.ProjekatIsa.security.CustomUserDetailsService;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	 @Autowired
	    private ReservationRepository reservationRepository;

	    @Autowired
	    private CustomUserDetailsService userService;
	    
	    @Autowired
	    private UserService uService;

	    @Autowired
	    private FlightReservationService flightReservationService;

	    @Autowired
	    private CarService carReservationService;

	    @Autowired
	    private RoomService roomReservationService;

	    @Autowired
	    private JavaMailSender javaMailSender;

	    @Autowired
	    private Environment env;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private FlightRepository flightRepository;
	    
	    @Autowired
	    private FlightReservationRepository res;

	    @Autowired
	    private SeatRepository seatRepository;

	    @Override
	    public List<Reservation> getReservationsByUser(String username) {

	        User user = (User) userService.loadUserByUsername(username);

	        List<Reservation> reservations = this.reservationRepository.findReservationsByUser(user);
	        if(reservations == null){
	            return null;
	        }
	        return reservations;
	    }

	    @Override
	    public List<Reservation> findAll() {
	        List<Reservation> reservations = reservationRepository.findAll();
	        if(reservations == null){
	            return null;
	        }
	        return reservations;
	    }

	    @Override
	    public Reservation createReservation(ReservationDto reservationDto) {

	        Reservation reservation = new Reservation();
	        reservation.setDateCreated(new Date());
	        reservation.setFlightReservation(this.flightReservationService.
	                createFlightReservation(reservationDto.getFlightReservation()));

	 /*       if (reservationDto.getCarReservation() != null) {
	            if (reservationDto.getCarReservation().get() != null) {
	                reservation.setCarReservation(this.carReservationService.
	                        createReservation(reservationDto.getCarReservation()));
	            }
	        }
*/
	   /*     if(reservationDto.getRoomReservation() != null){
	            if(reservationDto.getRoomReservation().getDepartureDate() != null){
	                RoomReservation roomReservation = this.roomReservationService.createRoomReservation(reservationDto.getRoomReservation());
	                reservation.setRoomReservation(roomReservation);
	            }
	        }*/

	        reservation.setUser(this.uService.findOneById(reservationDto.getId()));

	        reservation = this.reservationRepository.save(reservation);

	        return reservation;
	    }

	    @Override
	    public void cancelReservation(Long id) {
	        Reservation reservation = this.reservationRepository.findReservationById(id);

	/*        if (reservation.getCarReservation() != null) {
	            this.carReservationService.deleteReservation(reservation.getCarReservation().getId());
	        }

	        for (PassengerOnFlightSeat pass : reservation.getFlightReservation().getPassengersOnSeats()) {
	            pass.getSeat().setState("free");                        // oslobodi svako sediste
	            pass.setSeat(this.seatRepository.save(pass.getSeat()));
	        }

	        this.reservationRepository.deleteById(id);*/
	    }

	    @Override
	    public List<ReservationDto> getAllByAirlineId(Long id) {

	        List<Reservation> reservations = new ArrayList<>();
	        List<Reservation> allReservations = this.reservationRepository.findAll();

	        for (Reservation reservation : allReservations) {
	            Flight flight = this.flightRepository.getOne(reservation.getFlightReservation().getFlightId());

	          
	                reservations.add(reservation);
	           
	        }

	        List<ReservationDto> reservationDtos = new ArrayList<>();
	        for (Reservation reservation : reservations) {
	            reservationDtos.add(new ReservationDto(reservation));
	        }

	        return reservationDtos;
	    }

	    @Override
	    public void sendReservationEmail(Long id) throws MailException, InterruptedException {

	        FlightReservation reservation = this.res.findFlightReservationById(id);
	        Flight flight = this.flightRepository.findOneById(reservation.getFlightId());
	        Destination from = new Destination();
	        Destination to = new Destination();
	        for (Destination d : flight.getDestination()) {
	            if (d.getDescription().equals("to")) {
	                to = d;
	            } else if (d.getDescription().equals("from")) {
	                from = d;
	            }
	        }
	        System.out.println("Sending email...");

	        SimpleMailMessage mail = new SimpleMailMessage();
	        User u = this.userRepository.findOneById(reservation.getUserId());
	        mail.setTo(u.getEmail());
	        mail.setFrom(env.getProperty("spring.mail.username"));
	        mail.setSubject("New reservation");

	        String emailText = "Napravili ste rezervaciju ";
	        emailText = emailText.concat("Let od: " + from.getName() + " " + from.getCountry() + " " 
	             );
	        emailText = emailText.concat(" do: " + to.getName() + " " + to.getCountry() + ", " +
	                " ");

	        for (Passenger seat : reservation.getPassengersOnSeats()) {
	            emailText = emailText.concat("Za: "+seat.getPassengerName() + " "+seat.getPassengerLastName()+" Vas pasos: "+
	                    seat.getPassengerId());
	            emailText = emailText.concat(" Red: "+seat.getSeat().getSeatRow()+" broj sjedista: "+seat.getSeat().getSeatColumn()+ " class: "+seat.getSeat().getSeatClass());
	        }
/*	        if (reservation.getCarReservation() != null){

	            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
	            String pickUp = dateFormat.format(reservation.getCarReservation().getEndDate());
	            String dropOff = dateFormat.format(reservation.getCarReservation().getEndDate());

	            emailText = emailText.concat("Car reservation");
	            emailText = emailText.concat("Car: "+ reservation.getCarReservation().getCar().getPrice()+
	                    reservation.getCarReservation().getCar().getName()+" " );
	            }*/
	        mail.setText(emailText);

	        try {
	            javaMailSender.send(mail);
	            System.out.println("Email sent!");
	        } catch (Exception e) {
	            System.out.println("Mail not sent!");
	        }
	    }

}
