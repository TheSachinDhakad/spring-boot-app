package com.josh.services;

import java.util.List;

import com.josh.model.User;



public interface UserService {
	
	public User registerUser(User user);
	
	public User findUserById(Integer userId) throws Exception;
	
	public User findByUserEmail(String email);
	
	public User followUser(Integer userId1 , Integer userId2) throws Exception;
	
	public User updateUser(User user , Integer userId) throws Exception;
	
	public List<User> searchUser(String query);

}
