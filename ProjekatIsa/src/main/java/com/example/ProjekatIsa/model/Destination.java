package com.example.ProjekatIsa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "destination")
public class Destination {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "name", nullable = false, columnDefinition="VARCHAR(40)")
    private String name;

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
