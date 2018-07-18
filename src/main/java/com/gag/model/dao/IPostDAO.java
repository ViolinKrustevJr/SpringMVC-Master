package com.gag.model.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.gag.model.Post;
import com.gag.model.Section;
import com.gag.model.Tag;
import com.gag.model.User;

public interface IPostDAO {

	Post getPostsById(int id) throws Exception;

	Collection<Post> getPostsByTag(int... tags) throws Exception;

	Collection<Post> getPostsBySection(int sectionId) throws Exception;
	
	Collection<Post> getVideos() throws Exception;

	Collection<Post> getCommentedPosts(int userId) throws SQLException;
	
	Collection<Post> getVotedPosts(int userId) throws SQLException;

	Collection<Post> getPostsByOwner(int userId) throws Exception;

	Collection<Post> getFreshPosts() throws Exception;
	
	void savePost(Post p) throws Exception;

	void deletePost(Post p) throws Exception;

	void votePost(int userId, int postId, int vote) throws Exception;
	
	int getAllVotes(int postId) throws Exception;
}
