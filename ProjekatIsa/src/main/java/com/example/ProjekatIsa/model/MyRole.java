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
@Table(name="myrole")
public class MyRole implements GrantedAuthority {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	
	public MyRole() {
		super();
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	 /*@Id
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
			return this.name;
		}
		@Override
		public String toString() {
			return "MyRole [role_id=" + role_id + ", name=" + name.name() + "]";
		}*/
		
		

}
