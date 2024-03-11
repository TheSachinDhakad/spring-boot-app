package com.josh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.josh.model.Post;
import com.josh.response.ApiResponse;
import com.josh.services.PostService;

@RestController
public class PostController {
	@Autowired
	PostService postService;
	
	
	@PostMapping("/post/user/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post , @PathVariable Integer userId) throws Exception{
		Post createPost = postService.createNewPost(post, userId);
		
		return new ResponseEntity<>(createPost , HttpStatus.ACCEPTED);
				
		
	}
	
	@DeleteMapping("/posts/{postId}/user/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId ,@PathVariable Integer userId) throws Exception{
		String message = postService.detelePost(postId, userId);
		
		ApiResponse response = new  ApiResponse(message , true);
		return new ResponseEntity<ApiResponse>(response ,HttpStatus.OK );
	}

	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception{
		Post post = postService.findPostById(postId);
		
		return new ResponseEntity<Post>(post , HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){
		
		List<Post> posts = postService.findPostByUserId(userId);
		return new ResponseEntity<List<Post>>(posts , HttpStatus.OK);
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPost(){
		
		List<Post> posts = postService.findAllPost();
		return new ResponseEntity<List<Post>>(posts , HttpStatus.OK);
		
	}
	
	@PutMapping("/posts/save/{postId}/user/{userId}")
	public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId , @PathVariable Integer userId) throws Exception{
		Post post = postService.savedPost(postId, userId);
		
		return new ResponseEntity<Post>(post , HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/posts/like/{postId}/user/{userId}")
	public ResponseEntity<Post> likedPostHandler(@PathVariable Integer postId , @PathVariable Integer userId) throws Exception{
		Post post = postService.likePost(postId, userId);
		
		return new ResponseEntity<Post>(post , HttpStatus.ACCEPTED);
	}
}
