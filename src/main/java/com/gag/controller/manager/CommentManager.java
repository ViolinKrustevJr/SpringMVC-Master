package com.gag.controller.manager;

import com.gag.model.Comment;
import com.gag.model.Post;
import com.gag.model.User;
import com.gag.model.dao.CommentDAO;

public enum CommentManager {
	
	COMMENT_MANAGER;
	
	public void writeComment(Post post, Comment c) throws Exception {
		if (post == null) {
			throw new Exception("No post given");
		}
		if (c == null || c.getContent().trim().isEmpty()) {
			throw new Exception("Comment should not be empty");
		}
		CommentDAO.COMMENT_DAO.saveComment(post, c);
	}
	
	public void removeComment(Comment c) throws Exception {
		if (c == null) {
			throw new Exception("No comment was chosen");
		}
		CommentDAO.COMMENT_DAO.deleteComment(c);
	}
	
	public void changeComment(Comment c) throws Exception {
		if (c == null || c.getContent().trim().isEmpty()) {
			throw new Exception("No changes were made");
		}
		CommentDAO.COMMENT_DAO.updateComment(c);
	}
}
