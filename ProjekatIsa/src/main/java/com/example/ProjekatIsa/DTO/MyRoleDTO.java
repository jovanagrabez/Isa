package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Role;

public class MyRoleDTO {
	
	private Long id;
	private Role roles;
	
	public MyRoleDTO() {}
	
	public MyRoleDTO(Role roles) {
		this.roles = roles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Role getRoles() {
		return roles;
	}
	public void setRoles(Role roles) {
		this.roles = roles;
	}
	
	

}
