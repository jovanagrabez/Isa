package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Filijale;

public class FilijaleDTO {
	private Long id;
	private String grad;
	private String drzava;
	private String adresa;
	
	
	
	public FilijaleDTO() {
		
	}
	public FilijaleDTO(Long id, String grad, String drzava, String adresa) {
		super();
		this.id = id;
		this.grad = grad;
		this.drzava = drzava;
		this.adresa = adresa;
	}
	
	public FilijaleDTO(Filijale filijale) {
		this(filijale.getId(),filijale.getGrad(),filijale.getDrzava(),filijale.getAdresa());
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	

}
