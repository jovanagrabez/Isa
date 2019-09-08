package com.example.ProjekatIsa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rating_avio")
public class RatingAvio {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingflight_id", nullable = false, updatable = false)
    private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Aviocompany aviocompany;
	
	@Column(name="rate", nullable = false)
	private int rate;
	
	
	

	public RatingAvio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RatingAvio(Long id, User user, Aviocompany aviocompany, int rate) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Aviocompany getAvioCompany() {
		return aviocompany;
	}

	public void setAvioCompany(Aviocompany aviocompany) {
		this.aviocompany = aviocompany;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
	

}
