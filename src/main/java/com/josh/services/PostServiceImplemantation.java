package com.josh.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josh.model.Post;
import com.josh.model.User;
import com.josh.repo.PostRepo;
import com.josh.repo.UserRepo;

@Service
public class PostServiceImplemantation implements PostService {
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepo userRepo;

	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		
		User user = userService.findUserById(userId);
		
		
		Post newPost = new Post();
		
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setVideo(post.getVideo());
		newPost.setCretedAt(LocalDateTime.now());
		
		newPost.setUser(user);
		return postRepo.save(newPost);
	} 

	@Override
	public String detelePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		
		User user = userService.findUserById(userId);
		
		if(post.getUser().getId()!=user.getId()) {
			throw new Exception("You Can't delete another user post ");
			
		}
		postRepo.delete(post);
		return "post deleted...";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return postRepo.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		Optional<Post> opt = postRepo.findById(postId);
		
		if(opt.isEmpty()) {
			throw new Exception("Post not found Id : " + postId);
		}
		return opt.get();
	}

	@Override
	public List<Post> findAllPost() {
		// TODO Auto-generated method stub
		return postRepo.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		
		User user = userService.findUserById(userId);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}
		
		else {
			user.getSavedPost().add(post);
			userRepo.save(user);
		}
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		
		User user = userService.findUserById(userId);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}
		else {
			post.getLiked().add(user);
		}
		
		return postRepo.save(post);
	}

}
