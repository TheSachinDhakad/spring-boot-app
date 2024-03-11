package com.josh.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	 
	private String caption;
	
	private String image;
	
	private String video;
	
	@ManyToOne
	private User user;
	
	private LocalDateTime cretedAt;
	
	@OneToMany
	private List<User> liked  = new ArrayList<>() ;

	public Post() {
		
	}

	

	public Post(Integer id, String caption, String image, String video, User user, LocalDateTime cretedAt,
			List<User> liked) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.video = video;
		this.user = user;
		this.cretedAt = cretedAt;
		this.liked = liked;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCretedAt() {
		return cretedAt;
	}

	public void setCretedAt(LocalDateTime cretedAt) {
		this.cretedAt = cretedAt;
	}



	public List<User> getLiked() {
		return liked;
	}



	public void setLiked(List<User> liked) {
		this.liked = liked;
	}
	
	
	
	
	

}
