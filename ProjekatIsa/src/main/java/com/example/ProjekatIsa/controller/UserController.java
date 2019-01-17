package com.example.ProjekatIsa.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.example.ProjekatIsa.DTO.UserDTO;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.User;

import com.example.ProjekatIsa.service.UserService;

//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/users")
//@CrossOrigin(origins = "*")

public class UserController {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	    
	   
	    
	    @Autowired
	    private UserService userService;
	    
	    @RequestMapping("/logged")
	    public ResponseEntity<UserDTO> user(Principal user) {
			Optional<User> u = userService.findByEmail(user.getName());
			System.out.println(u.get().getLastName() + "przime");
			UserDTO userDTO = new UserDTO(u.get());
			System.out.println("a uloge -> "+userDTO.getRolesDTO().size());
			
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		}
	    
	    
	    @RequestMapping(value="/login", method = RequestMethod.POST, 
				consumes = MediaType.APPLICATION_JSON_VALUE, 
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<UserDTO> login(@RequestBody User user,HttpSession session,HttpServletRequest request){
			Optional<User> loggedUser = userService.login(user);
			if(loggedUser != null) {
			    HttpSession newSession = request.getSession();
			    newSession.setAttribute("loggedUser", loggedUser);
			    UserDTO logged = new UserDTO(loggedUser.get());
			    return new ResponseEntity<UserDTO>(logged,HttpStatus.OK);
			}
			UserDTO logged = null;
			return new ResponseEntity<UserDTO>(logged,HttpStatus.NOT_FOUND);
		}
	    
	    

		@RequestMapping(value="/all", method = RequestMethod.GET)
			public List<User>  getUsers() {
				
				System.out.println("Number of hotels: " + userService.getAll().size());
				
				return userService.getAll();
		}
		
	    
	  
	    
	   
}    
