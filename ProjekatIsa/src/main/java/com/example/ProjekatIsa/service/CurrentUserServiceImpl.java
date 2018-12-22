package com.example.ProjekatIsa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

import com.example.ProjekatIsa.model.CurrentUser;
import com.example.ProjekatIsa.service.*;


public class CurrentUserServiceImpl implements CurrentUserService {
private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);

	
	@Override
	public boolean canAccessUser(CurrentUser currentUser, Long userId) {
		LOGGER.debug("Checking if user={} has access to user={}", currentUser, userId);
		boolean admin=false;
		for(GrantedAuthority authority:currentUser.getAuthorities()){
			if(authority.getAuthority().equals("ADMIN")) {
				admin=true;
				break;
			}
			
		}
		return currentUser != null
		&& (admin || currentUser.getId().equals(userId));
			
		//return currentUser != null
		//		&& (currentUser.getAuthorities().c.getName().equals("ADMIN") || currentUser.getId().equals(userId));
	}

}
