package com.example.ProjekatIsa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.ProjekatIsa.DTO.AviocompanyDTO;


@Entity
@Table(name = "aviocompany")
public class Aviocompany implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aviocompany_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "name", nullable = false, columnDefinition="VARCHAR(40)")
    private String name;
	
	@Column(name = "description", nullable = false, columnDefinition="VARCHAR(50)")
    private String description;
	
	@Column(name = "adress", nullable = false, columnDefinition="VARCHAR(100)")
    private String adress;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "avio_destination", joinColumns = @JoinColumn(name = "aviocompany_id"), inverseJoinColumns = @JoinColumn(name = "destination_id"))   
	private Set<Destination> destination;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "avio_flights", joinColumns = @JoinColumn(name = "aviocompany_id"), inverseJoinColumns = @JoinColumn(name = "flight_id"))   
	private Set<Flight> flight;

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "avio_tickets", joinColumns = @JoinColumn(name = "aviocompany_id"), inverseJoinColumns = @JoinColumn(name = "ticket_id"))   
	private Set<Tickets> ticket;
	
	@OneToMany(mappedBy="aviocompany", fetch = FetchType.LAZY)
    private Set<RatingAvio> ratings;
	
	
	
	
	
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

	public Aviocompany(Long id, String name, String description, String adress) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.adress = adress;
	}

	
	public Aviocompany(AviocompanyDTO a) {
		 setId(a.getId());
	     setName(a.getName());
	     setDescription(a.getDescription());
	     setAdress(a.getAdress());
		
	}
	public Aviocompany() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	
	
	
	
	
}
