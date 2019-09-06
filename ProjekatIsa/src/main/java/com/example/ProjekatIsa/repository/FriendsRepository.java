package com.example.ProjekatIsa.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Friends;
import com.example.ProjekatIsa.model.User;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Long>{
	
	
	Friends findFriendsById(Long id);
	List<Friends> findAllByUser1OrUser2(User user, User user1);
	
}
