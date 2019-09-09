package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.FlightDest;

public class FlightDestDTO {
	
	 private Long id;

	    private DestinationDTO destination;

	    private Flight flight;      

	    private String description;

	    public FlightDestDTO(){}

	    public FlightDestDTO(FlightDest flightDestination){
	        id = flightDestination.getId();

	        description = flightDestination.getDescription();
	        //flight = new FlightShowInfoDto(flightDestination.getFlight());
	   //     destination = new DestinationDTO(id, description, description, description);
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public DestinationDTO getDestination() {
	        return destination;
	    }

	    public void setDestination(DestinationDTO destination) {
	        this.destination = destination;
	    }

	    public Flight getFlight() {
	        return flight;
	    }

	    public void setFlight(Flight flight) {
	        this.flight = flight;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

}
