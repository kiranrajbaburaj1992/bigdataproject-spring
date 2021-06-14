package com.bigdataproj.springboot.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface TableService {

	public List<LinkedHashMap<String, String>> getTableRows(String tablename);

	public List<String> getTables();

	public boolean deleteTable(String tablename, String rowid);

	public boolean deleteTable(String tablename);
	
	public LinkedHashMap<String,String> getTableRow(String tablename, String rowid);

	public void getColumnFamilies(String tablename);

	public void putRow(String tablename, HttpServletRequest request);

	public void createTable(HttpServletRequest request);
}
