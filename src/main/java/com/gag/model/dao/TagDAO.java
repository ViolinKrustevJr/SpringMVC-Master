package com.gag.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gag.model.Tag;
import com.gag.model.db.DBManager;

public enum TagDAO implements ITagDAO {

	TAG_DAO;
	private Connection con;

	private TagDAO() {
		con = DBManager.DB_MANAGER.getConnection();
	}

	@Override
	public void saveTag(Tag g) throws SQLException {
		String sql = "INSERT INTO tags (name) VALUES (?)";
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, g.getName());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		g.setId(rs.getInt(1));
		System.out.println(g.getId());
	}

	@Override
	public Tag getTagById(int id) throws SQLException {
		String sql = "SELECT id, name FROM tags WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Tag tag = null;
		while (rs.next()) {
			tag = new Tag(rs.getInt("id"), rs.getString("name"));
		}
		return tag;
	}

	@Override
	public List<Tag> getTagsCointainig(String chars) throws SQLException {
		List<Tag> tags = new ArrayList<>();
		String sql = "SELECT id, name FROM tags WHERE name LIKE ? LIMIT 10;";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, "%"+chars+"%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			tags.add(new Tag(rs.getInt("id"), rs.getString("name")));
		}
		return tags;
	}
}
