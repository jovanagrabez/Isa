package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.RatingAvio;

public class RatingAvioDTO {
	
	private Long id;
	private UserDTO user;
	private AviocompanyDTO aviocompany;
	private int rate;
	
	
	
	
	public RatingAvioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RatingAvioDTO(RatingAvio avio) {
		this.id = avio.getId();
		this.user = new UserDTO(avio.getUser());
		this.aviocompany = new AviocompanyDTO(avio.getAvioCompany());
		this.rate = avio.getRate();
	}
	
	public RatingAvioDTO(Long id, UserDTO user, AviocompanyDTO aviocompany, int rate) {
		super();
		this.id = id;
		this.user = user;
		this.aviocompany = aviocompany;
		this.rate = rate;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public AviocompanyDTO getAviocompany() {
		return aviocompany;
	}
	public void setAviocompany(AviocompanyDTO aviocompany) {
		this.aviocompany = aviocompany;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
	

}
