package com.example.ProjekatIsa.DTO;

import java.util.Set;

import com.example.ProjekatIsa.model.*;

public class AviocompanyDTO {
   
	private Long id;
    private String name;
    private String adress;
    private String description;
    private Set<Destination> destination;
    private Set<Flight> flight;
    
    public AviocompanyDTO(Aviocompany avio) {
		this(avio.getId(), avio.getName(), avio.getAdress(), avio.getDescription(), avio.getDestination(), avio.getFlight());
	}
    
    
	public AviocompanyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AviocompanyDTO(Long id, String name, String adress, String description, Set<Destination> destination,
			Set<Flight> flight) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.destination = destination;
		this.flight = flight;
	}

	
	

	public Set<Destination> getDestination() {
		return destination;
	}


	public void setDestination(Set<Destination> destination) {
		this.destination = destination;
	}


	public Set<Flight> getFlight() {
		return flight;
	}


	public void setFlight(Set<Flight> flight) {
		this.flight = flight;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
    
    
    
	
	
}
