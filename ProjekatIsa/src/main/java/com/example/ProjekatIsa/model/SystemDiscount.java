package com.example.ProjekatIsa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "system_discount")
public class SystemDiscount {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "system_discount_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "amount", nullable = true)
	private Double amount;
	
	@Column(name = "percent", nullable = true)
	private Double percent;

	
	
	public SystemDiscount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SystemDiscount(Long id, Double amount, Double percent) {
		super();
		this.id = id;
		this.amount = amount;
		this.percent = percent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}
	
	
}
