package com.example.ProjekatIsa.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import com.example.ProjekatIsa.DTO.UserDTO;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.model.Role;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.model.UserTokenState;
import com.example.ProjekatIsa.security.TokenUtils;
import com.example.ProjekatIsa.security.auth.JwtAuthenticationRequest;
import com.example.ProjekatIsa.service.RoleService;
import com.example.ProjekatIsa.service.UserService;

import javax.ws.rs.core.Context;
import org.owasp.encoder.Encode;
import org.springframework.mobile.device.Device;




@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")

public class UserController {	 
	 
	 @Autowired
	 TokenUtils tokenUtils;
	 
	 @Autowired
	 private RestTemplate restTemplate;
	 
	 @Autowired
	 private AuthenticationManager manager;
    
	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private RoleService roleService;
	    
	 @RequestMapping(value="/login",method = RequestMethod.POST)
	    public ResponseEntity<?> loginUser(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletRequest httpRequest, HttpServletResponse response, Device device, HttpServletRequest hr){

			System.out.println("login entered in AuthController");
			
	        if(!checkMail(authenticationRequest.getEmail())) {
	        	System.out.println("Nije dobar email");
	            return new ResponseEntity<>(new UserTokenState("error",(long) 0), HttpStatus.NOT_FOUND);
	        }


	       
	 
	       User user = userService.findUserByMail((authenticationRequest.getEmail()));
	        if(user!=null) {       
	        	
	        	System.out.println("email adresa" + user.getEmail() + "rolicaa" + user.getRoles().toString());
	        	
	        	System.out.println("Prosledjena pass: " + authenticationRequest.getPassword());
				System.out.println("Hasovana pass: " + user.getPassword());
				
				System.out.println("BLA BLA BLAAAAAAAAAA" + authenticationRequest.getPassword() );
				System.out.println("Uspesna prijava :), email: " + user.getEmail());
				
				/*if(org.springframework.security.crypto.bcrypt.BCrypt.checkpw(authenticationRequest.getPassword(), user.getPassword())){	
				System.out.println("Uspesna prijava :), email: " + user.getEmail());
				}else{
					return new ResponseEntity<>(new UserTokenState("error",(long) 0), HttpStatus.OK);
			
				}*/
				
				/*if(!user.isEnabled())
				{
					return new ResponseEntity<>(new UserTokenState("notActivated",(long) 0), HttpStatus.OK);

				}*/
	        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
	        headers.add("Content-Type", "application/json");
	       HttpEntity<JwtAuthenticationRequest> HReq=new HttpEntity<JwtAuthenticationRequest>(authenticationRequest,headers);
	       for(int i=0; i<user.getRoles().size(); i++) {
	    	   Role rolaAdmin = new Role();
	    	   Role rolaAdminAvio = new Role();
	    	   Role rolaAdminCar = new Role();
	    	   Role rolaAdminHotel = new Role();
	    	   Role rolaUser = new Role();
	    	   rolaUser = roleService.findById(1);
	    	   rolaAdmin = roleService.findById(2);
	    	   rolaAdminAvio = roleService.findById(3);
	    	   rolaAdminHotel = roleService.findById(4);
	    	   rolaAdminCar = roleService.findById(5);
    
	    	   System.out.println("rola usera  id je : " + rolaUser.getId());
	    	   
	    	   
	    	   if(user.getRoles().contains(rolaUser))
	           {
	           	System.out.println("Admin se loguje");
	            System.out.println(HReq + "" + JwtAuthenticationRequest.class);
	           	 ResponseEntity<?> res1 = restTemplate.postForEntity("http://localhost:8080/api/mainSecurity/setAuthentication", HReq, JwtAuthenticationRequest.class);

	           }
	           else if(user.getRoles().contains(rolaAdmin))
	           {
	           	System.out.println("klijent se loguje");
	           	 ResponseEntity<?> res2 = restTemplate.postForEntity("https://localhost:8080/api/mainSecurity/setAuthentication", HReq, JwtAuthenticationRequest.class);
	                
	           } 
	           else if(user.getRoles().contains(rolaAdminAvio)) {
	        	   System.out.println("agent se loguje");
	             	 ResponseEntity<?> res3 = restTemplate.postForEntity("https://localhost:8080/api/setAuthentication", HReq, JwtAuthenticationRequest.class);
	     
	        	   
	           }
	           else if(user.getRoles().contains(rolaAdminHotel)) {
	        	   System.out.println("agent se loguje");
	             	 ResponseEntity<?> res4 = restTemplate.postForEntity("https://localhost:8080/api/setAuthentication", HReq, JwtAuthenticationRequest.class);
	     
	        	   
	           }
	           else if(user.getRoles().contains(rolaAdminCar)) {
	        	   System.out.println("agent se loguje");
	             	 ResponseEntity<?> res5 = restTemplate.postForEntity("https://localhost:8080/api/setAuthentication", HReq, JwtAuthenticationRequest.class);
	     
	        	   
	           }
	       }
	       
				
				 final Authentication authentication = manager
			                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));


			        SecurityContextHolder.getContext().setAuthentication(authentication);

			        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
			        		  SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			        		  
			        		  for (GrantedAuthority authority : authorities) {
			        		    System.out.println("Authority: " + authority.getAuthority());
			        		  }
			        		
			        User user1 = (User) authentication.getPrincipal();
					String jwt = tokenUtils.generateToken(user1.getEmail(), device);
					int expiresIn = tokenUtils.getExpiredIn(device);
					
					return ResponseEntity.ok(new UserTokenState(jwt,(long) expiresIn));
	        }else
	        {
	        	System.out.println("User je null");
	        	return new ResponseEntity<>(new UserTokenState("error",(long) 0), HttpStatus.OK);

	        }
	       
	    }
	 
	 
	 
	 @RequestMapping(value = "/userprofile", method = RequestMethod.POST,
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
					public ResponseEntity<User> getProfile(@RequestBody String token) 
					{
					System.out.println("user profile u api useruuuuuuuuuuuu");
					    User notvalidUser = new User();

					
						System.out.println("IMA TOKEN: " + token);
						String email = tokenUtils.getUsernameFromToken(token);
						
						System.out.println("USERNAME: " + email);
						if(!checkMail(email)) {
							return  new ResponseEntity<User>(notvalidUser, HttpStatus.NOT_FOUND);
						}
						User user = (User) this.userService.findUserByMail(Encode.forHtml(email));
						return  new ResponseEntity<User>(user, HttpStatus.OK);
					}
	    
	    
	    public boolean checkCharacters(String data) {
			if(data.isEmpty()) {
				return false;
			}
			for(Character c :data.toCharArray()) {
				if(Character.isWhitespace(c)== false && Character.isLetterOrDigit(c) == false) {
					return false;
				}
			}
			
			return true;
		}
		public boolean checkId(String id) {
			if(id.isEmpty()) {
				return false;
			}
			for(Character c :id.toCharArray()) {
				if(!Character.isDigit(c)) {
					return false;
				}
			}
			return true;
		}
		public boolean checkMail(String mail) {
			if(mail.isEmpty()) {
				return false;
			}
			if(mail.contains(";")) {
				return false;
			}
			
			if(mail.contains(",")) {
				return false;
			}
			for(Character c:mail.toCharArray()) {
				if(Character.isWhitespace(c)) {
					return false;
				
				}
					
			}
			return true;
		}
	    		
		 @RequestMapping(value = "/logout", method = RequestMethod.GET)
		    public void logout(){
		    	
		    	System.out.println("Logout glavni back");
		    	SecurityContextHolder.clearContext();

		      
		    }
	    
	    

		@RequestMapping(value="/all", method = RequestMethod.GET)
			public List<User>  getUsers() {
				
				System.out.println("Number of hotels: " + userService.getAll().size());
				
				return userService.getAll();
		}
		
	    
	  
	    
	   
}    
