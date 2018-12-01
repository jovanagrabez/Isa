package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.RentACar;

public class RentACarDTO {
	
	private Long id;
    private String name;
    private String adress;
    private String description;
    
    public RentACarDTO(RentACar rentacar) {
		this(rentacar.getId(), rentacar.getName(), rentacar.getAdress(), rentacar.getDescription());
	}

	public RentACarDTO(Long id, String name, String adress, String description) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.description = description;
	}

	public RentACarDTO() {
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
