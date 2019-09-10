package com.example.ProjekatIsa.DTO;

import java.util.Date;
import java.util.Set;

import com.example.ProjekatIsa.model.Destination;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.Seat;
import com.example.ProjekatIsa.model.SeatArrangement;

public class FlightDTO {
	
	
	 private Long id;
	 private Date take_off;
	 private Date landing;
	 private double time;
	 private double travel_time;
	 private int number;
	 private SeatArrangement seatArrangement;
	    private Set<Seat> seats;
	 private Destination fromDest;
	 private Destination toDest;
	 private double averageRating;
	 private double numberOfRating;
	 private double sumRating;
	 private double economyPrice;
	private double premiumEconomyPrice;
	 private double businessPrice;
	private double firstPrice;
	private int distance;  

	
	
	private Set<Long> destination;
   private String baggage_description;
	
   
		 
		
   
	public Set<Long> getDestination() {
	return destination;
}
public void setDestination(Set<Long> destination) {
	this.destination = destination;
}
	public String getBaggage_description() {
	return baggage_description;
}
public void setBaggage_description(String baggage_description) {
	this.baggage_description = baggage_description;
}
	public FlightDTO(Long id, Date take_off, Date landing, double time, double travel_time, int number,
		SeatArrangement seatArrangement, Set<Seat> seats, Destination fromDest, Destination toDest,
		double averageRating, double numberOfRating, double sumRating, double economyPrice, double premiumEconomyPrice,
		double businessPrice, double firstPrice, int distance, String baggage_description) {
	super();
	this.id = id;
	this.take_off = take_off;
	this.landing = landing;
	this.time = time;
	this.travel_time = travel_time;
	this.number = number;
	this.seatArrangement = seatArrangement;
	this.seats = seats;
	this.fromDest = fromDest;
	this.toDest = toDest;
	this.averageRating = averageRating;
	this.numberOfRating = numberOfRating;
	this.sumRating = sumRating;
	this.economyPrice = economyPrice;
	this.premiumEconomyPrice = premiumEconomyPrice;
	this.businessPrice = businessPrice;
	this.firstPrice = firstPrice;
	this.distance = distance;
	this.baggage_description = baggage_description;
}
	public SeatArrangement getSeatArrangement() {
		return seatArrangement;
	}
	public void setSeatArrangement(SeatArrangement seatArrangement) {
		this.seatArrangement = seatArrangement;
	}
	public Set<Seat> getSeats() {
		return seats;
	}
	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}
	public Destination getFromDest() {
			return fromDest;
		}
		public void setFromDest(Destination fromDest) {
			this.fromDest = fromDest;
		}
		public Destination getToDest() {
			return toDest;
		}
		public void setToDest(Destination toDest) {
			this.toDest = toDest;
		}
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
			SeatArrangement seatArrangement, Set<Seat> seats, Destination fromDest, Destination toDest,
			double averageRating, double numberOfRating, double sumRating, double economyPrice,
			double premiumEconomyPrice, double businessPrice, double firstPrice, int distance, Set<Long> destination,
			String baggage_description) {
		super();
		this.id = id;
		this.take_off = take_off;
		this.landing = landing;
		this.time = time;
		this.travel_time = travel_time;
		this.number = number;
		this.seatArrangement = seatArrangement;
		this.seats = seats;
		this.fromDest = fromDest;
		this.toDest = toDest;
		this.averageRating = averageRating;
		this.numberOfRating = numberOfRating;
		this.sumRating = sumRating;
		this.economyPrice = economyPrice;
		this.premiumEconomyPrice = premiumEconomyPrice;
		this.businessPrice = businessPrice;
		this.firstPrice = firstPrice;
		this.distance = distance;
		this.destination = destination;
		this.baggage_description = baggage_description;
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
