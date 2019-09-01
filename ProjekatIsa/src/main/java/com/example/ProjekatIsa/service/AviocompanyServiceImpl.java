package com.example.ProjekatIsa.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Destination;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.repository.AviocompanyRepository;
import com.example.ProjekatIsa.repository.HotelRepository;

@Service
public class AviocompanyServiceImpl  implements AviocompanyService{
	
	@Autowired
	private AviocompanyRepository avioRepository;
	
	@Autowired
	private DestinationService destinationService;
	
	@Autowired
	private FlightService flightService;
	
	@Override
	public List<Aviocompany> getAll() {
		return avioRepository.findAll();
	}

	
	@Override
	public Aviocompany getCompanyByID(Long id) {
		
		return avioRepository.findOneById(id);
		
	}
	
	@Override
	public Aviocompany addAvioCompany(Aviocompany avioCompany) {
		return this.avioRepository.save(avioCompany);
	}
	
	@Override
	public Aviocompany updateAviocompany(Aviocompany avioCompany) {
		
		
	        Set<Flight> flights = new HashSet<>();
	        Set<Destination> availableDestinations = new HashSet<>();

	        for(Flight f : avioCompany.getFlight()){
	            flights.add(this.flightService.getFlightById(f.getId()));
	        }
	        for (Destination d : avioCompany.getDestination()) {
	            availableDestinations.add(this.destinationService.getDestinationById(d.getId()));
	        }

	        avioCompany.setDestination(availableDestinations);
	        avioCompany.setFlight(flights);
	        
	       
	        System.out.println("LETOVIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII" + availableDestinations.toString());
	        this.avioRepository.save(avioCompany);
	       
		return avioCompany;
	}
	
	@Override
    public boolean deleteAirline(Aviocompany aviocompany) {

        try {
            this.avioRepository.delete(aviocompany);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
