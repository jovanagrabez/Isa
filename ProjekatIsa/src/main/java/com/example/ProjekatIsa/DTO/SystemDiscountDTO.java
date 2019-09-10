package com.example.ProjekatIsa.DTO;

public class SystemDiscountDTO {
	private Long id;
	private Double amount;
	private Double percent;
	
	public SystemDiscountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SystemDiscountDTO(Long id, Double amount, Double percent) {
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
