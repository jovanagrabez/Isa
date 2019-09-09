package com.example.ProjekatIsa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name="reservation_room")
public class ReservationRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_room_id", nullable = false, updatable = false)
	private Long id; 
    
	@Column(name = "start_date", nullable = false)
	private Date startDate;
    
	@Column(name = "end_date", nullable = false)
	private Date endDate;
    
	@Column(name = "total_price")
	private Double totalPrice;
    
	@Column(name="category")
	private String category;
	
	@Column(name="num_people")
	private Long numPeople;
	
	@Column(name="reservation_status")
    private String reservationStatus;
    
	@Column(name="reservation_rating")
    private String reservationRating;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    
	public ReservationRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public ReservationRoom(Long id, Date startDate, Date endDate, Double totalPrice, String category, Long numPeople,
			String reservationStatus, String reservationRating, User user, Room room) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalPrice = totalPrice;
		this.category = category;
		this.numPeople = numPeople;
		this.reservationStatus = reservationStatus;
		this.reservationRating = reservationRating;
		this.user = user;
		this.room = room;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public Long getNumPeople() {
		return numPeople;
	}



	public void setNumPeople(Long numPeople) {
		this.numPeople = numPeople;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public String getReservationRating() {
		return reservationRating;
	}

	public void setReservationRating(String reservationRating) {
		this.reservationRating = reservationRating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
    
    
}
