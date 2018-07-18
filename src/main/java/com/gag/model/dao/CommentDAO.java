package com.gag.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gag.model.Comment;
import com.gag.model.Post;
import com.gag.model.User;
import com.gag.model.db.DBManager;

public enum CommentDAO implements ICommentDAO {

	COMMENT_DAO;
	private Connection con;
	
	private CommentDAO() {
		con = DBManager.DB_MANAGER.getConnection();
	}

	@Override
	public Comment getCommentById(int id) throws SQLException {
		String sql="SELECT id, date, content, user_id, post_id, parent_id FROM comments WHERE id =? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Comment comment = null;
		if (rs.next()) {
			comment = new Comment(rs.getInt("id"), rs.getDate("date"), 
											UserDAO.USER_DAO.getUserById(rs.getInt("user_id")), rs.getString("content"));
		}
		ps.close();
		rs.close();
		return comment;
	}

	@Override
	public void saveComment(Post p, Comment c) throws SQLException {
		String sql = "INSERT INTO comments (content, user_id, post_id) VALUES (?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getContent());
		ps.setInt(2, c.getOwner().getId());
		ps.setInt(3, p.getId());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void deleteComment(Comment c) throws SQLException {
		String sql = "DELETE FROM comments WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, c.getId());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void saveSubComment(int postId, int parentId, Comment child) throws SQLException {
		String sql = "INSERT INTO comments (content, user_id, post_id, parent_id) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, child.getContent());
		ps.setInt(2, child.getOwner().getId());
		ps.setInt(3, postId);
		ps.setInt(4, parentId);
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void voteComment(int userId, int commentId, int vote) throws SQLException {
		//Check if the user had already liked/disliked this post
				String sql = "SELECT vote FROM comments_have_votes WHERE comment_id=? AND user_id=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, commentId);
				ps.setInt(2, userId);
			    ResultSet rs=ps.executeQuery();
			    if(!rs.next()) {
			    	sql = "INSERT INTO comments_have_votes (user_id,comment_id,vote) VALUES (?,?,?)";
					ps = con.prepareStatement(sql);
					ps.setInt(1, userId);
					ps.setInt(2, commentId);
					ps.setInt(3, vote);
					ps.executeUpdate();
					return;
			    }else if(rs.getInt("vote")==vote) {
			             return;
			    }
			    vote+=rs.getInt("vote");
			    sql = "UPDATE comments_have_votes SET vote=? WHERE comment_id=? AND user_id=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, vote);
				ps.setInt(2, commentId);
				ps.setInt(3, userId);
				ps.executeUpdate();
				ps.close();
	}

	@Override
	public List<Comment> getCommentsByPost(int postId) throws SQLException {
		System.out.println(postId);
		String sql = "SELECT id, date, content, user_id, post_id, parent_id FROM comments WHERE post_id=? ORDER BY date DESC;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, postId);
		ResultSet rs = ps.executeQuery();
		List<Comment> comments = new ArrayList<>();
		while(rs.next()) {
			Comment com = new Comment(rs.getInt("id"), rs.getDate("date"),
					UserDAO.USER_DAO.getUserById(rs.getInt("user_id")), rs.getString("content"));
			comments.add(com);
		//	com.setReplies(getCommentsByComment(com));
		}
		ps.close();
		return comments;
	}

	@Override
	public void updateComment(Comment c) throws SQLException {
		String sql = "UPDATE comments SET content=? WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getContent());
		ps.setInt(2, c.getId());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public List<Comment> getCommentsByComment(Comment comment) throws SQLException {
		System.out.println(comment.getId());
		String sql = "SELECT c.id, c.date, c.content, c.user_id, c.post_id, c.parent_id FROM comments c"
					 + "JOIN comments p ON (c.parent_id = p.id) WHERE p.id = ?;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, comment.getId());
		ResultSet rs = ps.executeQuery(sql);
		
		List<Comment> comments = new ArrayList<>();
		System.out.println("kokoko");
		while(rs.next()) {
			System.out.println("kokoko");
			comments.add(new Comment(rs.getInt("id"), rs.getDate("date"),  
					               	UserDAO.USER_DAO.getUserById(rs.getInt("user_id")),
					               	rs.getString("content")));
		}
		ps.close();
		return comments;
	}


	@Override
	public int getAllVotes(int commentId) throws SQLException {
		String sql="SElECT SUM(vote) AS votes FROM comments_have_votes WHERE comment_id=?; ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, commentId);
		ResultSet rs = ps.executeQuery();
		return rs.next()? rs.getInt("votes"):0;	
	}
}
