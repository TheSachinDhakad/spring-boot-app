package com.josh.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josh.model.User;
import com.josh.repo.UserRepo;

@Service
public class UserServiceImplemantation implements UserService {
	@Autowired
	UserRepo userRepo;

	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastname(user.getLastname());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setGender(user.getGender());
		newUser.setId(user.getId());

		User saveUser = userRepo.save(newUser);
		return saveUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
		Optional<User> user = userRepo.findById(userId);

		if (user.isPresent()) {
			return user.get();
		}
		throw new Exception("user not found for id : " + userId);
	}

	@Override
	public User findByUserEmail(String email) {
		User user = userRepo.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer userId1, Integer userId2) throws Exception {
		User user1 = findUserById(userId1);
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(user1.getId());
		
		user1.getFollowing().add(user2.getId());
		
		userRepo.save(user1);
		userRepo.save(user2);
		
		return user1;
	}

	@Override
	public User updateUser(User updatedUser, Integer userId) throws Exception {
		Optional<User> user = userRepo.findById(userId);

		if (user.isEmpty()) {
			throw new Exception("user not found ");
		}

		User oldUser = user.get();

		if (updatedUser.getFirstName() != null) {
			oldUser.setFirstName(updatedUser.getFirstName());
		}

		if (updatedUser.getLastname() != null) {
			oldUser.setLastname(updatedUser.getLastname());
		}

		if (updatedUser.getEmail() != null) {
			oldUser.setEmail(updatedUser.getEmail());
		}

		User newUser = userRepo.save(oldUser);
		return newUser;
	}

	@Override
	public List<User> searchUser(String query) {
		return	 userRepo.searchUser(query);
		 
	}

}
