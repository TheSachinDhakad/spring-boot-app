package com.josh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josh.model.User;
import com.josh.repo.UserRepo;
import com.josh.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserService userService;

	@PostMapping("/user")
	public User createUser(@RequestBody User user) {

		

		User saveUser = userService.registerUser(user);

		return saveUser;

	}

	@GetMapping("/api/users")
	public List<User> getUsers() {

		List<User> list = userRepo.findAll();
		return list;
	}

	@GetMapping("/api/user/{userId}")
	public User getUserById(@PathVariable("userId") Integer userId) throws Exception {
		
		User user = userService.findUserById(userId);
		return user;
	}

	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") Integer userId, @RequestBody User updatedUser) throws Exception {
		User user = userService.updateUser(updatedUser, userId);
		return user;
	}

	
	@PutMapping("/api/users/follow/{userId1}/{userId2}")
	public User followUserhandler(@PathVariable Integer userId1 , @PathVariable Integer userId2) throws Exception {
		
		User user = userService.followUser(userId1, userId2);
		return user;
	}
	
	@GetMapping("/api/users/search")
	private List<User> searchUser(@RequestParam ("query") String query ){
		return userService.searchUser(query);
	}
	
	
	@DeleteMapping("/api/user/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) throws Exception {
		Optional<User> user = userRepo.findById(userId);

		if (user.isEmpty()) {
			throw new Exception("user not found ");
		}

		userRepo.delete(user.get());

		return "user deleted";

	}
}
