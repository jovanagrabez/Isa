package com.example.ProjekatIsa.DTO;

import java.util.List;

import com.example.ProjekatIsa.model.Flight;

public class FilterDTO {
	
	
	 private String airline;
	    private double fromDuration;
	    private double toDuration;
	    private double fromPrice;
	    private double toPrice;
	    private List<Flight> flights;

	    public FilterDTO(){}

	    public String getAirline() {
	        return airline;
	    }

	    public void setAirline(String airline) {
	        this.airline = airline;
	    }

	    public double getFromDuration() {
	        return fromDuration;
	    }

	    public void setFromDuration(double fromDuration) {
	        this.fromDuration = fromDuration;
	    }

	    public double getToDuration() {
	        return toDuration;
	    }

	    public void setToDuration(double toDuration) {
	        this.toDuration = toDuration;
	    }

	    public double getFromPrice() {
	        return fromPrice;
	    }

	    public void setFromPrice(double fromPrice) {
	        this.fromPrice = fromPrice;
	    }

	    public double getToPrice() {
	        return toPrice;
	    }

	    public void setToPrice(double toPrice) {
	        this.toPrice = toPrice;
	    }

	    public List<Flight> getFlights() {
	        return flights;
	    }

	    public void setFlights(List<Flight> flights) {
	        this.flights = flights;
	    }

}
