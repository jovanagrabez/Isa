package com.example.ProjekatIsa.DTO;

import java.util.Date;

import com.example.ProjekatIsa.model.Flight;

public class FlightDTO {
	
	
	 private Long id;
	 private Date take_off;
	 private Date landing;
	 private double time;
	 private double travel_time;
	 private int number;
	 private double averageRating;
		private double numberOfRating;
		private double sumRating;
		 private double economyPrice;
		 private double premiumEconomyPrice;
		 private double businessPrice;
		 private double firstPrice;
		 private int distance;  


		 
	public double getAverageRating() {
			return averageRating;
		}
		public void setAverageRating(double averageRating) {
			this.averageRating = averageRating;
		}
		public double getNumberOfRating() {
			return numberOfRating;
		}
		public void setNumberOfRating(double numberOfRating) {
			this.numberOfRating = numberOfRating;
		}
		public double getSumRating() {
			return sumRating;
		}
		public void setSumRating(double sumRating) {
			this.sumRating = sumRating;
		}
		public double getEconomyPrice() {
			return economyPrice;
		}
		public void setEconomyPrice(double economyPrice) {
			this.economyPrice = economyPrice;
		}
		public double getPremiumEconomyPrice() {
			return premiumEconomyPrice;
		}
		public void setPremiumEconomyPrice(double premiumEconomyPrice) {
			this.premiumEconomyPrice = premiumEconomyPrice;
		}
		public double getBusinessPrice() {
			return businessPrice;
		}
		public void setBusinessPrice(double businessPrice) {
			this.businessPrice = businessPrice;
		}
		public double getFirstPrice() {
			return firstPrice;
		}
		public void setFirstPrice(double firstPrice) {
			this.firstPrice = firstPrice;
		}
		public int getDistance() {
			return distance;
		}
		public void setDistance(int distance) {
			this.distance = distance;
		}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getTake_off() {
		return take_off;
	}
	public void setTake_off(Date take_off) {
		this.take_off = take_off;
	}
	public Date getLanding() {
		return landing;
	}
	public void setLanding(Date landing) {
		this.landing = landing;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public double getTravel_time() {
		return travel_time;
	}
	public void setTravel_time(double travel_time) {
		this.travel_time = travel_time;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public FlightDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	 
	 public FlightDTO(Long id, Date take_off, Date landing, double time, double travel_time, int number,
			double averageRating, double numberOfRating, double sumRating, double economyPrice,
			double premiumEconomyPrice, double businessPrice, double firstPrice, int distance) {
		super();
		this.id = id;
		this.take_off = take_off;
		this.landing = landing;
		this.time = time;
		this.travel_time = travel_time;
		this.number = number;
		this.averageRating = averageRating;
		this.numberOfRating = numberOfRating;
		this.sumRating = sumRating;
		this.economyPrice = economyPrice;
		this.premiumEconomyPrice = premiumEconomyPrice;
		this.businessPrice = businessPrice;
		this.firstPrice = firstPrice;
		this.distance = distance;
	}
	public FlightDTO(Flight f) {
		 this(f.getId(),f.getTake_off(),f.getLanding(),f.getTime(),f.getTravel_time(),
				 f.getNumber(),f.getAverageRating(),f.getNumberOfRating(),
				 f.getSumRating(),f.getEconomyPrice(),
				 f.getPremiumEconomyPrice(),
				 f.getBusinessPrice(),
				 f.getFirstPrice(),
				 f.getDistance());
	 }






}
