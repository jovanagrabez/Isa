package com.example.ProjekatIsa.model;

import java.util.Date;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="discount")
public class Discount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name = "date_from", nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm", timezone="GMT")
	@Temporal(TemporalType.DATE)
	private Date dateFrom;
	
	@Column(name = "date_to", nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm", timezone="GMT")
	@Temporal(TemporalType.DATE)
	private Date dateTo;
	
	
	
	@Column(name="discount",nullable = false)
	private int discount;
	
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "discount")
	private List<Car> cars;
	
	
	public Discount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Discount(Long id, Date dateFrom, Date dateTo, int discount) {
		super();
		this.id = id;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.discount = discount;
	}
	
	

	

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
	

}
