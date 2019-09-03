package com.example.ProjekatIsa.DTO;

public class FriendsDTO {
	
	
	    private Long user1;
	    private Long user2;
	    private boolean accepted;

	    public FriendsDTO() {}

	    public Long getUser1() {
	        return user1;
	    }

	    public void setUser1(Long user1) {
	        this.user1 = user1;
	    }

	    public Long getUser2() {
	        return user2;
	    }

	    public void setUser2(Long user2) {
	        this.user2 = user2;
	    }

	    public boolean isAccepted() {
	        return accepted;
	    }

	    public void setAccepted(boolean accepted) {
	        this.accepted = accepted;
	    }
}
