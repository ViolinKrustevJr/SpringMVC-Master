package com.gag.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.gag.model.Comment;
import com.gag.model.Post;
import com.gag.model.User;

public interface ICommentDAO {
    
	Comment getCommentById(int id) throws Exception;
	List<Comment> getCommentsByPost(int postId) throws Exception;
	List<Comment> getCommentsByComment(Comment comment) throws Exception;
	int getAllVotes(int commentId) throws Exception;
    void saveComment(Post p, Comment c) throws Exception;
    void deleteComment(Comment c) throws Exception;
    public void saveSubComment(int postId, int parentId, Comment child) throws Exception;
    void voteComment(int userId, int commentId, int voteType) throws Exception;
    void updateComment(Comment c) throws Exception;
}
