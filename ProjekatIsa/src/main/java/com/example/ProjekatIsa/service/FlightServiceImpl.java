package com.example.ProjekatIsa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.DTO.FlightDTO;
import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.repository.AviocompanyRepository;
import com.example.ProjekatIsa.repository.FlightRepository;


@Service
public class FlightServiceImpl implements FlightService {
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired 
	private AviocompanyService avioService;
	
	
	
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
	        return this.flightRepository.save(flight);
	    }

	    @Override
	    public Flight saveFlight(Flight flight) {
	        return this.flightRepository.save(flight);
	    }

	    @Override
	    public Flight updateFlight(FlightDTO flightDto) {

	    	   Flight flight = this.flightRepository.getOne(flightDto.getId());
	    	
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
	        return flight;
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


}
