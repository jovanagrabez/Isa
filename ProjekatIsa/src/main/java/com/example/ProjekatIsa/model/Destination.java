package com.example.ProjekatIsa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.ProjekatIsa.DTO.DestinationDTO;

@Entity
@Table(name = "destination")
public class Destination implements Serializable {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "name", nullable = false, columnDefinition="VARCHAR(40)")
    private String name;
	
	
	@Column(name = "country", nullable = false, columnDefinition="VARCHAR(40)")
    private String country;
	

	@ManyToMany(mappedBy="destination")
	private Set<Aviocompany> aviocompany;
	
	@ManyToMany(mappedBy="destination")
	private Set<Flight> flight;
	
	
	public Destination(DestinationDTO d) {
		
		this.country=d.getCountry();
		this.name=d.getName();
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public Destination() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Destination(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
}
