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
	
	Optional<User> findOneByEmail(String email);
	Set<User> findByIdIn(Set<Long> ids);
	List<User> findAll();
	User findOneById(Long id);
	@Query("Select u from User u where UPPER(u.firstName)LIKE CONCAT('%',UPPER(:firstName),'%') or UPPER(u.lastName)LIKE CONCAT('%',UPPER(:lastName),'%')")
	List<User> findAllUsersFandL(@Param("firstName") String username,@Param("lastName")String lastName);


}
