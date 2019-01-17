package com.example.ProjekatIsa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
public class MyRole implements GrantedAuthority {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "role_id", nullable = false, updatable = false)
	    private Long role_id;
	 
	 
	 
	 @Enumerated(EnumType.STRING)
	 @Column(name = "name", nullable = false) 
	    private Role name;
	 
	  public MyRole(){
	    	super();
	    }
	    public MyRole(Role role){
	    	super();
	    	this.name=role;
	    	
	    }

	    
		public Long getRole_id() {
			return role_id;
		}
		public void setRole_id(Long role_id) {
			this.role_id = role_id;
		}
		public Role getName() {
			return name;
		}

		public void setName(Role name) {
			this.name = name;
		}
		@Override
		public String getAuthority() {
			// TODO Auto-generated method stub
			return this.name.name();
		}
		@Override
		public String toString() {
			return "MyRole [role_id=" + role_id + ", name=" + name.name() + "]";
		}
		
		

}
