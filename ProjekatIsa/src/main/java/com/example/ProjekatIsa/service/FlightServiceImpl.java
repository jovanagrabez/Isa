package com.example.ProjekatIsa.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.DTO.FlightDTO;
import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.Seat;
import com.example.ProjekatIsa.repository.AviocompanyRepository;
import com.example.ProjekatIsa.repository.FlightRepository;


@Service
public class FlightServiceImpl implements FlightService {
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired 
	private AviocompanyService avioService;
	
	@Autowired 
	private SeatService seatService;
	
	@Autowired 
	private SeatArrangementService seatAService;
	
	 @Override
	    public List<Flight> getAllFlights() {
	        return this.flightRepository.findAll();
	    }

	    @Override
	    public Flight getFlightById(Long id) {
	        return this.flightRepository.getOne(id);
	    }

	    @Override
	    public Flight addFlight(Flight flight) {
	    	this.seatAService.saveSeatArrangement(flight.getSeatArrangement());
	        return this.flightRepository.save(flight);
	    }

	    @Override
	    public Flight saveFlight(Flight flight) {
	        return this.flightRepository.save(flight);
	    }

	    @Override
	    public Flight updateFlight(Flight flightDto) {

	    	for(Seat seat: flightDto.getSeats())
	    	    this.seatService.addSeat(seat);
	    	
	    	this.seatAService.addSeatArrangement(flightDto.getSeatArrangement());
	    	   this.flightRepository.save(flightDto);
	    	
	 /*       Aviocompany airline = this.avioService.getAirlineById(flightDto.getAirlineId());

	        Flight flight = this.flightRepository.getOne(flightDto.getId());

	        flight = new Flight(flightDto);
	        flight.setAirline(airline);

	        flight.setDestinations(new HashSet<>());
	        List<FlightDestination> flightDestinations = new ArrayList<>();
	        int flightChanges = 0;
	        for (Long destId : flightDto.getDestinations()) {
	            Destination destination = this.destinationService.getDestinationById(destId);          //pretvori dto u objekat
	            if (destination == null) {continue;}
	            FlightDestination flightDestination = new FlightDestination(flight, destination);

	            if (destId.equals(flightDto.getToDest())){
	                flightDestination.setDescription("arrival");
	            }else if (destId.equals(flightDto.getFromDest())){
	                flightDestination.setDescription("departure");
	            } else {
	                flightDestination.setDescription("connecting");
	                flightChanges++;
	            }
	            flightDestinations.add(flightDestination);
	         }
	        flight.setFlightChanges(flightChanges);
	        flight = this.flightRepository.save(flight);
	        this.flightDestinationService.deleteAndAddNewFlightDestinationsByFlight(flight.getId(), flightDestinations);     //brise stare veze

*/
	        return flightDto;
	    }

	    @Override
	    @Transactional
	    public boolean deleteFlight(Flight flight) {

	        boolean success;
	       try {
	           this.flightRepository.deleteById(flight.getId());
	           success = true;
	       } catch (Exception e) {
	           success = false;
	       }
	       return success;
	    }
	    
	    @Override
	    public Flight updateSeats(Flight flightDto) {
	    	
	    	
	    	 Flight flight = this.flightRepository.findOneById(flightDto.getId());
	         List<Seat> seats = new ArrayList<>();
	         
	         for (Seat seat : flightDto.getSeats()) {
	        	 
	             if (seat.getSeatClass() != null) {
	                 if (seat.getSeatClass().equals("ECONOMY")) {            // postavi cenu ako je novo sediste, ili je klasa promenjena
	                     seat.setPrice(flight.getEconomyPrice());
	                 } else if (seat.getSeatClass().equals("PREMIUM_ECONOMY")) {
	                     seat.setPrice(flight.getPremiumEconomyPrice());
	                 } else if (seat.getSeatClass().equals("BUSINESS")) {
	                     seat.setPrice(flight.getBusinessPrice());
	                 } else if (seat.getSeatClass().equals("FIRST")) {
	                     seat.setPrice(flight.getFirstPrice());
	                 }
	             }
	             seat = this.seatService.updateSeat(seat);                 // snimi promenjeno sediste
	             seats.add(seat);
	         }

	         for (Seat seat : seats) {
	             boolean contains = false;
	             for (Seat savedSeat : flight.getSeats()) {
	                 if (seat.getId().equals(savedSeat.getId())) {
	                     contains = true;
	                 }
	             }
	             if (!contains) {           
	            	 this.seatService.addSeat(seat);// ako je novo dodaj ga u flight
	                 flight.getSeats().add(seat);
	             }
	         }

	         flight = this.flightRepository.save(flight);
	         return flight;

	    }


}
