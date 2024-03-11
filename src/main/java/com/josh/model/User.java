package com.josh.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.criteria.CriteriaBuilder.In;

@Entity

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String firstName;
	private String lastname;
	private String email;
	private String password;
	
	private String gender;
	
	private List<Integer> followers = new ArrayList<Integer>();
	
	private List<Integer> following  = new ArrayList<Integer>();
	
	@JsonIgnore
	@ManyToMany
	private List<Post> savedPost =  new ArrayList<>();
	
	public User() {
		
	}
	
	

	public User(Integer id, String firstName, String lastname, String email, String password, String gender,
			List<Integer> followers, List<Integer> following, List<Post> savedPost) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.followers = followers;
		this.following = following;
		this.savedPost = savedPost;
	}



	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public List<Integer> getFollowers() {
		return followers;
	}



	public void setFollowers(List<Integer> followers) {
		this.followers = followers;
	}



	public List<Integer> getFollowing() {
		return following;
	}



	public void setFollowing(List<Integer> following) {
		this.following = following;
	}



	public List<Post> getSavedPost() {
		return savedPost;
	}



	public void setSavedPost(List<Post> savedPost) {
		this.savedPost = savedPost;
	}
	
	
	
	
	
}
