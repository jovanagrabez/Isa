package com.example.ProjekatIsa.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.ProjekatIsa.model.MyRole;
import com.example.ProjekatIsa.model.User;

public class UserDTO {
	
private Long id;
	//bla bla bla
	
	private String firstName;
	private String lastName;
	private String email;	
	private String password;	
	private String city;
	private String phoneNumber;
	private Double points;
	private boolean firstLogin;
	private boolean verified = false;
	private boolean enabled = true;	
	private List<MyRoleDTO> rolesDTO;
	
	
	
	
	public UserDTO() {
		
	}
	public UserDTO(Long id, String firstName, String lastName, String email, String password, String city,
			String phoneNumber, boolean verified, boolean enabled, Double points, boolean firstLogin) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.verified = verified;
		this.enabled = enabled;
		this.points = points;
		this.firstLogin = firstLogin;
		
	}
	
	
public UserDTO(User user) {
		
		this(user.getId(), user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),
				user.getLastName(),user.getCity(),user.isVerified(),user.isEnabled(), user.getPoints(),user.isFirstLogin());
		rolesDTO = new ArrayList<>();
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public List<MyRoleDTO> getRolesDTO() {
		return rolesDTO;
	}
	public void setRolesDTO(List<MyRoleDTO> rolesDTO) {
		this.rolesDTO = rolesDTO;
	}
	public Double getPoints() {
		return points;
	}
	public void setPoints(Double points) {
		this.points = points;
	}
	public boolean isFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	




}
