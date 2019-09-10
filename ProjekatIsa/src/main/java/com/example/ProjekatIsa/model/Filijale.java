package com.example.ProjekatIsa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.ProjekatIsa.DTO.FilijaleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "filijale")

public class Filijale implements Serializable {
	
	public static final long serialVersionUID = 785264;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filijale_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name="grad", nullable = false)
	private String grad;
	
	@Column(name="drzava", nullable = false)
	private String drzava;
	
	@Column(name="adresa", nullable = false)
	private String adresa;
	
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name="rentacar_id")
	private RentACar rentacar;
	
	@JsonIgnore
	@OneToMany(mappedBy="filijale",fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Car> cars;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	

	public Filijale(Long id,String grad, String drzava, String adresa, RentACar rentACar) {
		super();
		this.id = id;
		this.grad = grad;
		this.drzava = drzava;
		this.adresa = adresa;
		this.rentacar = rentACar;
	}

	public Filijale() {
		// TODO Auto-generated constructor stub
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public RentACar getRentACar() {
		return rentacar;
	}

	public void setRentACar(RentACar rentacar) {
		this.rentacar = rentacar;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	
	public Filijale(FilijaleDTO f) {
		setId(f.getId());
		setGrad(f.getGrad());
		setDrzava(f.getDrzava());
		setAdresa(f.getAdresa());
	}
	
	public void addCar(Car c) {
		if(this.cars==null) {
			this.cars = new ArrayList<Car>();
		}
		
		this.cars.add(c);
	}
	
	
	
	

}
