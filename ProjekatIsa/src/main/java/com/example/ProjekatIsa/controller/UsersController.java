package com.example.ProjekatIsa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.ProjekatIsa.model.CurrentUser;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.service.UserService;

public class UsersController {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
	    
	    @Autowired
	    private UserService userService;

	    @RequestMapping("/users")
	    public ModelAndView getUsersPage() {
	        LOGGER.debug("Users page triggered");
	        return new ModelAndView("users", "users", userService.getAllUsers());
	    }
	    
	   

}
