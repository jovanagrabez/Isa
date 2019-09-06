package com.example.ProjekatIsa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table
@Entity
public class Seat implements Serializable{
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "seat_id")
	    private Long id;

	    @Column
	    private int seatRow;

	    @Column
	    private int seatColumn;

	    @Column
	    private String seatClass;

	    @Column
	    private String state;   

	    @Column
	    private double price;


	    @Column
	    private double discountPrice;

	    public Seat(){}

	    public Seat(int seatRow,int seatColumn, String state, String seatClass, double price, double discountPrice){
	        this.seatClass = seatClass;
	        this.seatColumn = seatColumn;
	        this.seatRow = seatRow;
	        this.state = state;
	        this.price = price;
	        this.discountPrice = discountPrice;
	    }

	    public int getSeatRow() { return seatRow; }

	    public void setSeatRow(int seatRow) { this.seatRow = seatRow; }

	    public int getSeatColumn() { return seatColumn; }

	    public void setSeatColumn(int seatColumn) { this.seatColumn = seatColumn; }

	    public String getSeatClass() { return seatClass; }

	    public void setSeatClass(String seatClass) { this.seatClass = seatClass; }

	    public String getState() { return state; }

	    public void setState(String taken) { this.state = taken; }

	    public Long getId() { return id; }

	    public void setId(Long id) { this.id = id; }

	    public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }

	    public double getDiscountPrice() {
	        return discountPrice;
	    }

	    public void setDiscountPrice(double discountPrice) {
	        this.discountPrice = discountPrice;
	    }

}
