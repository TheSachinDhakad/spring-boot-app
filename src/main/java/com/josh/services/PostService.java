package com.josh.services;

import java.util.List;

import com.josh.model.Post;

public interface PostService {

	Post createNewPost (Post post , Integer userID) throws Exception;
	
	String detelePost(Integer postId , Integer userId) throws Exception;
	
	List<Post> findPostByUserId(Integer userId);
	
	Post findPostById(Integer postId) throws Exception;
	
	List<Post> findAllPost();
	Post savedPost(Integer postId , Integer userId) throws Exception;
	
	Post likePost(Integer postId , Integer userId) throws Exception;
}

