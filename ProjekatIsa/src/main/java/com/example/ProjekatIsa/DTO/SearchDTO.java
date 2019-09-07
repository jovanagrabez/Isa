package com.example.ProjekatIsa.DTO;

import java.util.Date;

public class SearchDTO {
	
	
	 private String from;

	    private String to;

	    private Date fromDate;

	    private Date toDate;

	    private int type;

	    private String seatClass;

	    private int persons;

	    public SearchDTO() {}

	    public SearchDTO(String from, String to, Date fromDate, Date toDate, int type, String seatClass, int persons) {
	        this.from = from;
	        this.to = to;
	        this.fromDate = fromDate;
	        this.toDate = toDate;
	        this.type = type;
	        this.seatClass = seatClass;
	        this.persons = persons;
	    }

	    public String getFrom() {
	        return from;
	    }

	    public void setFrom(String from) {
	        this.from = from;
	    }

	    public String getTo() {
	        return to;
	    }

	    public void setTo(String to) {
	        this.to = to;
	    }

	    public Date getFromDate() {
	        return fromDate;
	    }

	    public void setFromDate(Date fromDate) {
	        this.fromDate = fromDate;
	    }

	    public Date getToDate() {
	        return toDate;
	    }

	    public void setToDate(Date toDate) {
	        this.toDate = toDate;
	    }

	    public int getType() {
	        return type;
	    }

	    public void setType(int type) {
	        this.type = type;
	    }

	    public String getSeatClass() {
	        return seatClass;
	    }

	    public void setSeatClass(String seatClass) {
	        this.seatClass = seatClass;
	    }

	    public int getPersons() {
	        return persons;
	    }

	    public void setPersons(int persons) {
	        this.persons = persons;
	    }

}
