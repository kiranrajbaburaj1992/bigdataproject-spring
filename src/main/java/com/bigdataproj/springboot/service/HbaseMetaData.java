package com.bigdataproj.springboot.service;

import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class HbaseMetaData  implements HbaseMetaDataService {
    private Admin hBaseAdmin;
    private Connection connection;
    
    @Autowired
	AppConfig config;

    public HbaseMetaData () throws IOException {
    }
    
    /** get all Table names **/
    @Override
    public List<String> getTableNames(String regex) throws IOException {

		this.connection = ConnectionFactory.createConnection(config.getconfig());
        this.hBaseAdmin = connection.getAdmin();
        Pattern pattern=Pattern.compile(regex);
        List<String> tableList = new ArrayList<String>();
        TableName[] tableNames=hBaseAdmin.listTableNames();
        for (TableName tableName:tableNames){
            if(pattern.matcher(tableName.toString()).find()){
                tableList.add(tableName.toString());
            }
        }
        return tableList;
    }
    
    /** Get all columns **/
    @Override
    public Set<String> getColumns(String hbaseTable) throws IOException {
        return getColumns(hbaseTable, 10000);
    }
    
    /** get all columns from the table **/
    @Override
    public Set<String> getColumns(String hbaseTable, int limitScan) throws IOException {
    	

		this.connection = ConnectionFactory.createConnection(config.getconfig());
        this.hBaseAdmin = connection.getAdmin();
        
        Set<String> columnList = new TreeSet<String>();

		Table hTable = connection.getTable(TableName.valueOf(hbaseTable));
        
        Scan scan=new Scan();
        scan.setFilter(new PageFilter(limitScan));
        ResultScanner results = hTable.getScanner(scan);
        for(Result result:results){
        	
        	for(Cell cell : result.listCells()) {
				String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyOffset());
				String qualifier = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
				String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
				System.out.println("Qualifier : " + qualifier
						+ " : Value : " + value);
				columnList.add(
                        new String(family) + ":" +
                                new String(qualifier)
                );
			}
        }
        return columnList;
    }
}
