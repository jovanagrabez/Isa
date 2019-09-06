package com.example.ProjekatIsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.FriendsDTO;
import com.example.ProjekatIsa.model.Friends;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.service.FriendsService;
import com.example.ProjekatIsa.service.UserService;

@RestController
@RequestMapping(value="/friends")
public class FriendsController {

	
	  @Autowired
	    private FriendsService friendsService;
	    @Autowired
	    private UserService userService;

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
}
