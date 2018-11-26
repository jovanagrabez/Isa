package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Hotel;

public class AviocompanyDTO {
   
	private Long id;
    private String name;
    private String adress;
    private String description;
    
    public AviocompanyDTO(Aviocompany avio) {
		this(avio.getId(), avio.getName(), avio.getAdress(), avio.getDescription());
	}
    
    
	public AviocompanyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AviocompanyDTO(Long id, String name, String adress, String description) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.description = description;
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
