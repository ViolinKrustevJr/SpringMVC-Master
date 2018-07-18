package com.gag.model.dao;

import java.util.Collection;

import com.gag.model.Section;

public interface ISectionDAO {

	Section getSectionByName(String name) throws Exception;

	Collection<Section> getAll() throws Exception;

}
