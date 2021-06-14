package com.bigdataproj.springboot.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface HbaseMetaDataService {
	
	/** get all Table names **/
	List<String> getTableNames(String regex) throws IOException;

	/** Get all columns **/
	Set<String> getColumns(String hbaseTable) throws IOException;

	/** get all columns from the table **/
	Set<String> getColumns(String hbaseTable, int limitScan) throws IOException;

}
