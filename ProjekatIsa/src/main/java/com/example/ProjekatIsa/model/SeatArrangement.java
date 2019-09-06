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
public class SeatArrangement implements Serializable {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column
	    private Long id;

	    @Column
	    private String name;

	    @Column
	    private int seatRows;

	    @Column
	    private int seatColumns;

	    public SeatArrangement(){}

	    public String getName() {  return name; }

	    public void setName(String name) { this.name = name; }

	    public Long getId() { return id; }

	    public void setId(Long id) { this.id = id; }

	    public int getSeatRows() {
	        return seatRows;
	    }

	    public void setSeatRows(int seatRows) {
	        this.seatRows = seatRows;
	    }

	    public int getSeatColumns() {
	        return seatColumns;
	    }

	    public void setSeatColumns(int seatColumns) {
	        this.seatColumns = seatColumns;
	    }

}
