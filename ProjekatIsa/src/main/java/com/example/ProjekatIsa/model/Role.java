package com.example.ProjekatIsa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;


@Entity
@Table(name="role")
public class Role {
	@Id
    @GeneratedValue
    private int id;
	
	@Enumerated(EnumType.STRING)
    //@NaturalId
	@Column(name = "name",nullable = false)
    private RoleName name;
    
 
    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
        name = "roles_privileges", 
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "privilege_id", referencedColumnName = "id"))
    private Set<MyRole> privileges;
    
    public Role() {
    	
    }

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public RoleName getName() {
		return name;
	}


	public void setName(RoleName name) {
		this.name = name;
	}
	

	public Set<MyRole> getPrivileges() {
		return privileges;
	}


	public void setPrivileges(Set<MyRole> privileges) {
		this.privileges = privileges;
	}
}
