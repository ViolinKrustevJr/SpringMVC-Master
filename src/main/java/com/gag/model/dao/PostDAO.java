package com.gag.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gag.model.Post;
import com.gag.model.Tag;
import com.gag.model.User;
import com.gag.model.db.DBManager;

public enum PostDAO implements IPostDAO {

	POST_DAO;

	private Connection con;

	private PostDAO() {
		con = DBManager.DB_MANAGER.getConnection();
	}

	@Override
	public List<Post> getPostsByTag(int... tags) throws Exception {
		List<Post> posts = new ArrayList<>();
		StringBuilder tagSQL = new StringBuilder();

		for (int i = 0; i < tags.length; i++) {
			if (i == tags.length - 1) {
				tagSQL.append("?");
			} else {
				tagSQL.append("?,");
			}
		}

		String sql = "SELECT post_id FROM posts_have_tags WHERE tag_id IN (" + tagSQL + ")";
		PreparedStatement ps = con.prepareStatement(sql);

		for (int i = 0; i < tags.length; i++) {
			ps.setInt(i + 1, tags[i]);
		}

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Post p = (Post) POST_DAO.getPostsById(rs.getInt("post_id"));
			posts.add(p);
		}
		return posts;
	}

	@Override
	public List<Post> getPostsBySection(int sectionId) throws SQLException {
		List<Post> posts = new ArrayList<>();
		String sql = "SELECT id,image_url, title, date, user_id, is_video FROM posts WHERE section_id=? ORDER BY date DESC";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, sectionId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			User owner = UserDAO.USER_DAO.getUserById(rs.getInt("user_id"));
			Post p = new Post(owner)
					.id(rs.getInt("id"))
					.imageURL(rs.getString("image_url"))
					.title(rs.getString("title"))
					.date(rs.getTimestamp("date")
					.toLocalDateTime())
					.video(rs.getBoolean("is_video"))
					.id(rs.getInt("id"));
			posts.add(p);
		}
        ps.close();
        rs.close();
		return posts;
	}

	@Override
	public List<Post> getPostsByOwner(int userId) throws SQLException {
		List<Post> posts = new ArrayList<>();
		String sql = "SELECT id, image_url, title, date, user_id, is_video FROM posts WHERE user_id=? ORDER BY date DESC";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			User owner = UserDAO.USER_DAO.getUserById(rs.getInt("user_id"));
			Post p = new Post(owner).imageURL(rs.getString("image_url")).title(rs.getString("title"))
					.date(rs.getTimestamp("date").toLocalDateTime())
					.video(rs.getBoolean("is_video"))
					.id(rs.getInt("id"));
			posts.add(p);
		}

		return posts;
	}
	
	@Override
	public List<Post> getCommentedPosts(int userId) throws SQLException {
		List<Post> posts = new ArrayList<>();
		String sql = "SELECT p.id, p.image_url, p.title, p.date, p.user_id, p.section_id, p.is_video FROM posts p"
					+ " JOIN comments c ON (c.post_id = p.id and c.user_id = p.user_id) WHERE p.user_id=?;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			User owner = UserDAO.USER_DAO.getUserById(rs.getInt("user_id"));
			Post p = new Post(owner).imageURL(rs.getString("image_url")).title(rs.getString("title"))
					.date(rs.getTimestamp("date").toLocalDateTime())
					.video(rs.getBoolean("is_video"))
					.id(rs.getInt("p.id"));
			posts.add(p);
		}
        ps.close();
        rs.close();
		return posts;
	}
	
	@Override
	public List<Post> getVotedPosts(int userId) throws SQLException {
		List<Post> posts = new ArrayList<>();
		String sql = "SELECT p.id, p.image_url, p.title, p.date, p.user_id, p.section_id, p.is_video FROM posts p "
					+ "JOIN posts_have_votes pv ON (p.id = pv.post_id)  WHERE pv.user_id=?;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			User owner = UserDAO.USER_DAO.getUserById(rs.getInt("user_id"));
			Post p = new Post(owner)
					.imageURL(rs.getString("image_url"))
					.title(rs.getString("title"))
					.date(rs.getTimestamp("date").toLocalDateTime())
					.video(rs.getBoolean("is_video"))
					.id(rs.getInt("p.id"));
			posts.add(p);
		}
		 ps.close();
	     rs.close();
		return posts;
	}

	@Override
	public List<Post> getFreshPosts() throws SQLException {
		List<Post> posts = new ArrayList<>();
		String sql = "SELECT id,image_url, title, date, user_id, is_video FROM posts ORDER BY date DESC ";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);

		while (rs.next()) {
			User owner = UserDAO.USER_DAO.getUserById(rs.getInt("user_id"));
			Post p = new Post(owner)
					.id(rs.getInt("id"))
					.imageURL(rs.getString("image_url"))
					.title(rs.getString("title"))
					.date(rs.getTimestamp("date").toLocalDateTime())
					.video(rs.getBoolean("is_video"));
			posts.add(p);
		}

	    rs.close();
		return posts;
	}

	@Override
	public void savePost(Post p) throws SQLException {
		String sql = "INSERT INTO posts (image_url, title, user_id, section_id, is_video) VALUES (?,?,?,?,?) ";
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, p.getImageURL());
		ps.setString(2, p.getTitle());
		ps.setInt(3, p.getOwner().getId());
		ps.setInt(4, p.getSection().getId());
		ps.setBoolean(5, p.getVideo());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		p.id(rs.getInt(1));
         
		// setting the tags
		for (int i = 0; i < p.getTags().size(); i++) {
			sql = "INSERT INTO posts_have_tags (post_id,tag_id) VALUES (?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, p.getId());
			System.out.println(p.getId());
			ps.setInt(2, p.getTags().get(i).getId());
			System.out.println(p.getTags().get(i).getId());
			ps.executeUpdate();
		}
		 ps.close();
	     rs.close();
	}

	@Override
	public void deletePost(Post p) throws SQLException {
		String sql = "DELETE FROM posts where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, p.getId());
		ps.executeUpdate();
	}

	@Override
	public Post getPostsById(int postId) throws SQLException {
		System.out.println(postId);
		String sql = "SELECT image_url, title, date, user_id, is_video FROM posts WHERE id=? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, postId);
		ResultSet rs = ps.executeQuery();
		rs.next();
		User owner = UserDAO.USER_DAO.getUserById(rs.getInt("user_id"));
		Post p = new Post(owner)
				.imageURL(rs.getString("image_url"))
				.title(rs.getString("title"))
				.date(rs.getTimestamp("date").toLocalDateTime())
				.video(rs.getBoolean("is_video"))
				.id(postId)
		        .comments(CommentDAO.COMMENT_DAO.getCommentsByPost(postId));
		ps.close();
		rs.close();
		return p;
	}

	@Override
	public void votePost(int userId, int postId, int vote) throws SQLException {
		//Check if the user had already liked/disliked this post
		String sql = "SELECT vote FROM posts_have_votes WHERE post_id=? AND user_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, postId);
		ps.setInt(2, userId);
	    ResultSet rs=ps.executeQuery();
	    if(!rs.next()) {
	    	sql = "INSERT INTO posts_have_votes (user_id,post_id,vote) VALUES (?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, postId);
			ps.setInt(3, vote);
			ps.executeUpdate();
			return;
	    }else if(rs.getInt("vote")==vote) {
	             return;
	    }
	    vote+=rs.getInt("vote");
	    sql = "UPDATE posts_have_votes SET vote=? WHERE post_id=? AND user_id=?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, vote);
		ps.setInt(2, postId);
		ps.setInt(3, userId);
		ps.executeUpdate();
		ps.close();
		rs.close();
		
	}

	@Override
	public int getAllVotes(int postId) throws SQLException {
		String sql="SElECT SUM(vote) AS votes FROM posts_have_votes WHERE post_id=?; ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, postId);
		ResultSet rs = ps.executeQuery();
		return rs.next()? rs.getInt("votes"):0;	
	}
	
	@Override
	public List<Post> getVideos() throws Exception {
		List<Post> videos = new ArrayList<>();
		String sql = "SELECT id, image_url, title, date, user_id FROM posts WHERE is_video=1 ORDER BY date DESC ";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		while (rs.next()) {
			User owner = UserDAO.USER_DAO.getUserById(rs.getInt("user_id"));
			Post p = new Post(owner)
					.id(rs.getInt("id"))
					.imageURL(rs.getString("image_url"))
					.title(rs.getString("title"))
					.date(rs.getTimestamp("date").toLocalDateTime());
			videos.add(p);
		}
		return videos;
	}

}
