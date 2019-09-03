package com.example.ProjekatIsa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.DTO.FriendsDTO;
import com.example.ProjekatIsa.model.Friends;
import com.example.ProjekatIsa.model.User;

@Service
public interface FriendsService {

	    List<Friends> getAll();
	    List<Friends> getFriendsByUser(User user);
	    Friends getFriendsById(Long id);
	    Friends updateFriendship(Friends friends);
	    Friends newFrendship(FriendsDTO friends);
	    void deleteFriendship(Long id);
	    List<User> searchForNewFriends(String nameLastName, Long id); 
	
}
