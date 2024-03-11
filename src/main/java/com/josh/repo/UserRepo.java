package com.josh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.josh.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);
	
	
	@Query("select u from User u Where u.firstName LIKE %:query% OR u.lastname LIKE %:query% OR u.email LIKE %:query%")
	public List<User> searchUser(String query);
	

}
