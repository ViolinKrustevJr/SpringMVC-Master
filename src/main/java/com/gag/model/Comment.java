package com.gag.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Comment {

	private int id;
	private User owner;
	private Date date;
	private String content;
	private List<Comment> replies;
	
	public Comment( User user, String content) {
		this.owner=user;
		this.content=content;
		this.replies=new ArrayList<>();
	}

	public Comment(int id, Date date, User user, String content) {
		this( user, content);
		this.id = id;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getOwner() {
		return this.owner;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public List<Comment> getReplies() {
		return Collections.unmodifiableList(replies);
	}
	
	public void setReplies(List<Comment> replies) {
		this.replies = replies;
	}
	
	public void addReply(Comment c) {
		this.replies.add(c);
	}
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
