package com.gag.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Post {

	private int id;
	private User owner;
	private Section section;
	private String imageURL;
	private LocalDateTime date;
	private String title;
	private boolean video;
	private List<Tag> tags;
	private List<Comment> comments;

	public Post(User owner) {
		this.owner = owner;
		this.comments = new ArrayList<>();
		this.tags = new ArrayList<>();
	};

	public Post section(Section section) {
		this.section = section;
		return this;
	}

	public Post title(String title) {
		this.title = title;
		return this;
	}

	public Post date(LocalDateTime date) {
		this.date = date;
		return this;
	}

	public Post id(int id) {
		this.id = id;
		return this;
	}

	public Post video(boolean isVideo) {
		this.video = isVideo;
		return this;
	}
	
	public Post imageURL(String imageURL) {
		this.imageURL = imageURL;
		return this;
	}
	
	public Post comments(List<Comment> comments) {
		this.comments=comments;
		return this;
	}

	public int getId() {
		return id;
	}

	public String getImageURL() {
		return imageURL;
	}

	public User getOwner() {
		return this.owner;
	}

	public Section getSection() {
		return section;
	}

	public String getTitle() {
		return title;
	}

	public List<Comment> getComments() {
		return Collections.unmodifiableList(comments);
	}

	public void deleteComment(Comment c) {
		this.comments.remove(c);
	}

	public boolean getVideo() {
		return video;
	}

	public void addTags(Tag... tags) {
		for (int i = 0; i < tags.length; i++) {
			this.tags.add(tags[i]);
		}
	}

	public void addComment(Comment c) {
		comments.add(c);
	}

	public List<Tag> getTags() {
		return Collections.unmodifiableList(tags);
	}

}
