package com.example.ProjekatIsa.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.ProjekatIsa.model.CurrentUser;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.MyRole;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.model.UserCreateForm;
import com.example.ProjekatIsa.model.VerificationToken;
import com.example.ProjekatIsa.service.UserService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	    
	   // @Autowired
	    //private RegistrationListener rg;
	    
	    @Autowired
	    private UserService userService;

	    
	    

	/*    @RequestMapping(
	    		value = "/angularUser",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
	    public User getUserPageAngular(@ModelAttribute("currentUser") CurrentUser currentUser) {
	    	System.out.println("treba da vrati:" +currentUser);
	        return currentUser.getUser();
	    }*/
	    
	  
	  
	   /* @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
	    @RequestMapping("/user/{id}")
	    public ModelAndView getUserPage(@PathVariable Long id) {
	        LOGGER.debug("User page triggered for user={}", id);
	        return new ModelAndView("user", "user", userService.getUserById(id)
	                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
	    }*/
	    @RequestMapping(
				value = "/getAll", 
				method = RequestMethod.GET, 
				produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User>  getUsers() {
			
			System.out.println("Number of users: " + userService.getAll().size());
			
			return userService.getAll();
}
	    
	   
}    
