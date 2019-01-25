package com.example.ProjekatIsa.config;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterForm {
	
	 @NotBlank
	    @Size(min = 3, max = 50)
	    private String name;

	    @NotBlank
	    @Size(max = 60)
	    @Email
	    private String email;
	    
	    private Set<String> role;
	    
	    @NotBlank
	    @Size(min = 6, max = 40)
	    private String password;
	    
	    @NotBlank
	    @Size(min = 3, max = 50)
	    private String city;
	    
	    @NotBlank
	    @Size(min = 3, max = 50)
	    private String phone;
	    
	    @NotBlank
	    @Size(min = 3, max = 50)
	    private String lastname;

	    public String getFirsName() {
	        return name;
	    }

	    public void setFirsName(String name) {
	        this.name = name;
	    }

	   

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPasswordHash() {
	        return password;
	    }

	    public void setPasswordHash(String password) {
	        this.password = password;
	    }
	    
	    public Set<String> getRole() {
	    	return this.role;
	    }
	    
	    public void setRole(Set<String> role) {
	    	this.role = role;
	    }

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getPhoneNumber() {
			return phone;
		}

		public void setPhoneNumber(String phone) {
			this.phone = phone;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

}
