package com.example.ProjekatIsa.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.ProjekatIsa.model.MyRole;
import com.example.ProjekatIsa.model.User;

public class UserDTO {
	
private Long id;
	//bla bla bla
	
	private String name;	
	private String email;	
	private String password;	
	private String lastname;
	private String city;
	private String phone;
	private boolean verified = false;

	
	private List<MyRoleDTO> rolesDTO;

	public UserDTO(Long id, String name, String email, String password, String lastname, String city, String phone,
			boolean verified) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.lastname = lastname;
		this.city = city;
		this.phone = phone;
		this.verified = verified;

	
	}

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
public UserDTO(User user) {
		
		this(user.getId(), user.getFirstName(),user.getEmail(),user.getPassword(),user.getLastName(),user.getCity(),user.getPhoneNumber(),user.isVerified());
		rolesDTO = new ArrayList<>();
		
	}

 public List<MyRoleDTO> getRolesDTO() {
	return rolesDTO;
  }

  public void setRolesDTO(List<MyRoleDTO> rolesDTO) {
	this.rolesDTO = rolesDTO;
}




}
