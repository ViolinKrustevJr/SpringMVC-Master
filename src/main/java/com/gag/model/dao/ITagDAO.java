package com.gag.model.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.gag.model.Tag;

public interface ITagDAO {

	void saveTag(Tag g) throws Exception;

	Tag getTagById(int id) throws Exception;

	Collection<Tag> getTagsCointainig(String chars) throws Exception;
}
