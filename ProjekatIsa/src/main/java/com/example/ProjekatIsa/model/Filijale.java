package com.example.ProjekatIsa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private RentACar rentACar;
	
	@OneToMany(mappedBy="filijale",fetch = FetchType.LAZY,orphanRemoval = true)
	private Set<Car> cars;

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
		this.rentACar = rentACar;
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
		return rentACar;
	}

	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}
	
	
	
	

}
