package com.bigdataproj.springboot.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface TableService {

	public List<LinkedHashMap<String, String>> getTableRows(String tablename);

	public List<String> getTables();

	public boolean deleteTable(String tablename, String rowid);
	
	public LinkedHashMap<String,String> getTableRow(String tablename, String rowid);
}
