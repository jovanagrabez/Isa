package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rating_room")

public class RatingRoom implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingRoom_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "value", nullable = false)
    private int value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public RatingRoom(int value) {
		super();
		this.value = value;
	}

	public RatingRoom() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
