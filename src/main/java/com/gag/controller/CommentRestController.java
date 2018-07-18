package com.gag.controller;


import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gag.model.Comment;
import com.gag.model.Post;
import com.gag.model.User;
import com.gag.model.dao.CommentDAO;
import com.gag.model.dao.PostDAO;


@Controller
public class CommentRestController {
	
	
	@RequestMapping(value="/add/comment", method=RequestMethod.POST)
	@ResponseBody
	public void addComment(Model m, HttpSession session,
			                @RequestParam("content") String content,
			                @RequestParam("postId") int postId
			               ) throws SQLException {
		if (session.getAttribute("user") == null) {
			return;
		}
        Comment c=new Comment((User)session.getAttribute("user"),content);
		CommentDAO.COMMENT_DAO.saveComment(PostDAO.POST_DAO.getPostsById(postId), c);
	}
	
	@RequestMapping(value="/comment/upvote",method = RequestMethod.POST)
	public void upvote(@RequestParam int id, HttpSession session, Model model) throws SQLException {
		if (session.getAttribute("user") == null) {
			return;
		}
        User user = (User) session.getAttribute("user");
        CommentDAO.COMMENT_DAO.voteComment(user.getId(), id, 1);      
    }
	
	@RequestMapping(value="/comment/downvote",method = RequestMethod.POST)
	public void downvote(@RequestParam int id, HttpSession session, Model model) throws SQLException {
		if (session.getAttribute("user") == null) {
			return ;
		}
        User user = (User) session.getAttribute("user");
        CommentDAO.COMMENT_DAO.voteComment(user.getId(), id, -1); 
    }
    
	@RequestMapping(value = "/comment/votes/{commentId}", method = RequestMethod.GET)
	@ResponseBody
	public int getAllVotes(@PathVariable int commentId) throws SQLException {
		System.out.println(PostDAO.POST_DAO.getAllVotes(commentId));
		return CommentDAO.COMMENT_DAO.getAllVotes(commentId);
	}
	
	@RequestMapping(value="/comment/delete",method = RequestMethod.POST)
	public void upvote(@RequestParam int id, HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			return;
		}
		Comment c = CommentDAO.COMMENT_DAO.getCommentById(id);
        User user = (User) session.getAttribute("user");
        if(c.getOwner().getId()==user.getId()) {
        	CommentDAO.COMMENT_DAO.deleteComment(c);
        }
    }
}
