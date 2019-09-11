package com.example.ProjekatIsa.controller;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

//import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.FriendsDTO;
import com.example.ProjekatIsa.DTO.UserDTO;
import com.example.ProjekatIsa.model.Friends;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.FriendsRepository;
import com.example.ProjekatIsa.service.FriendsService;
import com.example.ProjekatIsa.service.UserService;

@RestController
@RequestMapping(value="/friends")
public class FriendsController {

	
	  @Autowired
	    private FriendsService friendsService;
	    @Autowired
	    private UserService userService;
	    
	    @Autowired
	    FriendsRepository friendsRepository;

	    @GetMapping(value = "/{username}")
	
	    public ResponseEntity<List<Friends>> getFriendUsersByUsername(@PathVariable String username) {

	        User user =  (User) this.userService.loadUserByUsername(username);
	        if (user == null) {
	            return ResponseEntity.notFound().build();
	        }

	        List<Friends> friends = this.friendsService.getFriendsByUser(user);
	        return new ResponseEntity(friends, HttpStatus.OK);
	    }

	    @GetMapping(value = "/all")
	 
	    public ResponseEntity<List<Friends>> getAllFriends() {

	        List<Friends> friends = this.friendsService.getAll();
	        if (friends == null) {
	            return ResponseEntity.notFound().build();
	        }

	        return new ResponseEntity(friends, HttpStatus.OK);
	    }

	    @GetMapping(value = "/accept/{id}")
	 
	    public ResponseEntity<Friends> acceptFriendship(@PathVariable Long id){

	        Friends friends = this.friendsService.getFriendsById(id);
	        if (friends == null) {
	            return ResponseEntity.notFound().build();
	        }
	        friends.setAccepted(true);
	        friends = this.friendsService.updateFriendship(friends);
	        return new ResponseEntity(friends, HttpStatus.OK);
	    }

	    @PostMapping
	  
	    public ResponseEntity<Friends> newFriendship(@RequestBody FriendsDTO friendsDto){

	        Friends friends = this.friendsService.newFrendship(friendsDto);

	        if (friends == null) {
	            return ResponseEntity.notFound().build();
	        }

	        return new ResponseEntity(friends, HttpStatus.OK);
	    }

	    @DeleteMapping(value = "/{id}")
	
	    public ResponseEntity<List<Friends>> deleteFriendship(@PathVariable Long id){

	        Friends friends = this.friendsService.getFriendsById(id);
	        if (friends == null) {
	            return ResponseEntity.notFound().build();
	        }
	        this.friendsService.deleteFriendship(id);

	        return new ResponseEntity<List<Friends>>(this.friendsService.getAll(),HttpStatus.OK);
	    }

	    @GetMapping(value = "/search/{id}/{nameLastName}")
	
	    public ResponseEntity<List<User>> searchForNewFriends(@PathVariable Long id, @PathVariable String nameLastName){

	        List<User> users = this.friendsService.searchForNewFriends(nameLastName, id);

	        return new ResponseEntity(users, HttpStatus.OK);
	    }
	    
	    @RequestMapping(value="/sortForm/{param}",
				method = RequestMethod.POST)
		public ResponseEntity<?> sortForm(@PathVariable("param") String param, @RequestBody List<Friends> user){
			System.out.println("usao u sortiranjee");
			
			List<Friends> sorted = new ArrayList<Friends>();
			
			List<User> svi = new ArrayList<User>();

			for(Friends acc : user) {
				System.out.println("naziv je" + acc.getUser1().getFirstName());
			}
			Collator collator = Collator.getInstance(Locale.US);
			String[] paramArray = param.split("=");
			String item = paramArray[0];
			String order = paramArray[1];
			boolean descending=false; 
			boolean ascending=false; 
			
			if(order.equals("descending")) {
				descending = true;
			}
			
			
			if(item.equals("ime") && order.equals("ascending") ) {
				Collections.sort(
		                user,
		                (user1, user2) ->( user1.getUser2().getFirstName().compareTo(user2.getUser1().getFirstName())));
				
			}
			
			

	      
	      if(item.equals("prezime") && order.equals("ascending") ) {
	    	  Collections.sort(
		                user,
		                (user1, user2) ->( user1.getUser2().getLastName().compareTo(user2.getUser1().getLastName())));
				
				System.out.println("Name" + sorted);
			}
	      
	    
			
		
			return  new ResponseEntity<List<Friends>>(sorted, HttpStatus.OK);
			
		}

		
}
