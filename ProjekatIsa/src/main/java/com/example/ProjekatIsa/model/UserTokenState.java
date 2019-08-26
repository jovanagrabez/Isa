package com.example.ProjekatIsa.model;

public class UserTokenState {
	
	private String accessToken;
	private Long expiresIn;
	private int expiresIn2;

	
	public UserTokenState() {
		this.accessToken = null;
		this.expiresIn = null;
	}
	
	

	public UserTokenState(String accessToken, Long expiresIn) {
		super();
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}



	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

}
