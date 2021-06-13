package com.bigdataproj.springboot.service;

import java.util.List;

import com.bigdataproj.springboot.model.TweetsUser;

public interface TweetsService {

	public List<TweetsUser> getTweetsUsers();

	public List<String> getTables();

	public boolean deleteTable(String tablename, String rowid);
}
