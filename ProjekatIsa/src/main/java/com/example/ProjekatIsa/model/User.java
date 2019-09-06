package com.example.ProjekatIsa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ProjekatIsa.DTO.MyRoleDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "user")
public class User implements Serializable,UserDetails {

	private static final long serialVersionUID = 155L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "first_name")
    public String firstName;
	
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    
    @Email
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    
    @Column(name="city",nullable=true)
    private String city;
    
    @Column(name="phone_number",nullable=true)
    private String phoneNumber;
    
    @Column(name = "enabled", nullable = true)
    private boolean enabled;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( 
            name = "user_roles", 
            joinColumns =  @JoinColumn(
              name = "user_id", referencedColumnName = "user_id"), 
            inverseJoinColumns = @JoinColumn(
            	name = "role_id", referencedColumnName = "id")) 
        private Collection<Role> roles;
    
    @Column(name="verified")
	private boolean verified;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CarReservation> rezCar;
    

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
		return passwordHash;
	}

	public void setPassword(String passwordHash) {
		this.passwordHash = passwordHash;
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
	
	 public Collection<Role> getRoles(){
	    	return roles;
	    }
	    
		public void setRoles(Collection<Role> roles) {
			this.roles = roles;
		}
		
		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}


	public User(String email) {
		super();
		this.email = email;
	}
	
	public User() {
		super();
		
		
	}


	public User(String firstName, String lastName, String email, String passwordHash, String city, String phoneNumber,boolean verified,List<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.passwordHash = passwordHash;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.verified = verified;
		this.roles = roles;
	}
	
	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email +
                ", passwordHash='" + passwordHash.substring(0, 10) +
                ", role=" + roles +
                '}';
    }

	public boolean isVerified() {
		return verified;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
		
		if(!this.roles.isEmpty()) {
			Role r = roles.iterator().next();
			List<MyRole> privileges = new ArrayList<MyRole>();
			for(MyRole p : r.getPrivileges()) {
				privileges.add(p);
			}
			
			return privileges;
		}
		
		return null;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
	
    
    
    
}
