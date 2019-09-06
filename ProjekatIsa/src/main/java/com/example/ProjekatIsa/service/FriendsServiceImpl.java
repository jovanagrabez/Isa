package com.example.ProjekatIsa.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.ProjekatIsa.DTO.FriendsDTO;
import com.example.ProjekatIsa.model.Friends;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.FriendsRepository;
import com.example.ProjekatIsa.repository.UserRepository;

@Service
public class FriendsServiceImpl implements FriendsService {
	
	    @Autowired
	    private FriendsRepository friendsRepository;
	    @Autowired
	    private UserRepository userRepository;
	    
	    @Autowired
	    private JdbcTemplate javaTemplate;
	   

	    @Override
	    public List<Friends> getAll() {
	        return this.friendsRepository.findAll();
	    }

	    @Override
	    public List<Friends> getFriendsByUser(User user) {

	        List<Friends> friends = this.friendsRepository.findAllByUser1OrUser2(user, user);

	        return friends;
	    }

	    @Override
	    public Friends getFriendsById(Long id) {
	        return this.friendsRepository.findFriendsById(id);
	    }

	    @Override
	    public Friends updateFriendship(Friends friends) {

	        Friends savedFriendship = this.friendsRepository.findFriendsById(friends.getId());

	        savedFriendship.setAccepted(friends.isAccepted());

	        savedFriendship = this.friendsRepository.save(savedFriendship);

	        return savedFriendship;
	    }

	    @Override
	    public Friends newFrendship(FriendsDTO friendsDto) {
	        Friends friends = new Friends();
	        friends.setUser1(this.userRepository.findOneById(friendsDto.getUser1()));
	        friends.setUser2(this.userRepository.findOneById(friendsDto.getUser2()));
	        friends.setAccepted(false);
	        return this.friendsRepository.save(friends);
	    }

	    @Override
	    public void deleteFriendship(Long id) {

	        this.friendsRepository.deleteById(id);

	    }

	    @Override
	    public List<User> searchForNewFriends(String nameLastName, Long id) {

	        String[] searchString = nameLastName.split(" ");
	        List<User> users = new ArrayList();
	        for (String s : searchString) {
	            if (!s.equals("")) {
	            	 users.addAll(this.userRepository.findUsersByFirstNameLikeOrLastNameLikeOrFirstNameContainsOrLastNameContains(s, s, s, s));
	        }
	        }
	        User loggedUser = this.userRepository.findOneById(id);

	        List<Friends> friendsOfLoggedUser = this.friendsRepository.findAllByUser1OrUser2(loggedUser, loggedUser);

	        List<User> usersWhoAreNotFriends = new ArrayList<>();
	        for (User u : users){
	            boolean matches = true;
	           
	            for (Friends f : friendsOfLoggedUser) {
	                 if (u.getUsername().equals(f.getUser1().getUsername()) ||    // ako se nalazi medju prijateljima
	                                u.getUsername().equals(f.getUser2().getUsername())) {
	                            matches = false;
	                            break;
	                        }
	                    }
	                               
	                
	            
	            if (matches) {
	                usersWhoAreNotFriends.add(u);
	            }
	     
	        }
	        return usersWhoAreNotFriends;
	    }


}
