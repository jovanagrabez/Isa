package com.example.ProjekatIsa.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;



public class CurrentUser extends org.springframework.security.core.userdetails.User {
	
	private static final long serialVersionUID = 5119798847634486546L;
	private User user;
	private Collection<GrantedAuthority> pomocni;
    public CurrentUser(User user) {
        //super(user.getEmail(), user.getPasswordHash(), AuthorityUtils.createAuthorityList());
        super(user.getEmail(),user.getPasswordHash(),user.isEnabled(),true,true,true,AuthorityUtils.createAuthorityList());
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }
    
    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                "} " + super.toString();
    }

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		pomocni=new ArrayList<GrantedAuthority>();
        for(MyRole role: user.getRoles()){
        //	System.out.println("uloga:"+role.getName());
			
        	SimpleGrantedAuthority pom=new SimpleGrantedAuthority(role.getName());
        	System.out.println("pom je:"+pom);
        	pomocni.add(pom);
        	System.out.println("ovo radi"+pom.getAuthority());
        }
        return pomocni;
	}

}
