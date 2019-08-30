package com.example.ProjekatIsa.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.User;

@Repository


public interface UserRepository extends JpaRepository<User, Long>{
    
	Optional<User> findByEmail(String email);

	Set<User> findByIdIn(Set<Long> ids);
	List<User> findAll();
	User findOneByEmail(String mail);
	User findByFirstName(String firstName);
	User findOneById(Long id);



	

}
