package com.bigdataproj.springboot.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableManager implements TableService {

	@Autowired
	AppConfig config;

	@Autowired
	HbaseMetaData hbaseMetaData;

	@Override
	public List<LinkedHashMap<String,String>> getTableRows(String tablename) {

		List<LinkedHashMap<String,String>> list = new ArrayList<LinkedHashMap<String,String>>();
		try {
			Connection connection = ConnectionFactory.createConnection(config.getconfig());
			Table table = connection.getTable(TableName.valueOf(tablename));
			Scan scan1 = new Scan();
			ResultScanner scanner1 = table.getScanner(scan1);


			for (Result result = scanner1.next(); (result != null); result = scanner1.next()) {
				LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
				map.put("id",Bytes.toString(result.getRow()));
				for(Cell cell : result.listCells()) {
					String qualifier = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
					String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
					System.out.println("Qualifier : " + qualifier
							+ " : Value : " + value);

					map.put(qualifier,value);
				}
				list.add(map);
			}

			scanner1.close();

		}catch(Exception e) {
			System.out.print(e);
		}

		return list;
	}

	@Override
	public List<String> getTables() {

		List<String> list = new ArrayList<String>();
		try {
			Connection connection = ConnectionFactory.createConnection(config.getconfig());
			HTableDescriptor tableDescriptor[] = connection.getAdmin().listTables();
			for (int i=0; i<tableDescriptor.length; i++) {
				list.add(tableDescriptor[i].getNameAsString());
			}
		}catch(Exception e) {
			System.out.print(e);
		}
		return list;
	}

	@Override
	public boolean deleteTable(String tablename, String rowid) {

		try {
			Connection connection = ConnectionFactory.createConnection(config.getconfig());
			Table table = connection.getTable(TableName.valueOf(tablename));

			//Delete Object
			Delete del = new Delete(rowid.getBytes());
			table.delete(del);
			return true;

		}catch(Exception e) {
			System.out.print(e);
		}
		return false;
	}

	@Override
	public boolean deleteTable(String tablename) {

		try {
			Connection connection = ConnectionFactory.createConnection(config.getconfig());

			// Delete Table
			Admin admin = connection.getAdmin();
			TableName tableName = TableName.valueOf(tablename);
			admin.disableTable(tableName);
			admin.deleteTable(tableName);

			return true;

		}catch(Exception e) {
			System.out.print(e);
		}
		return false;

	}

	@Override
	public LinkedHashMap<String, String> getTableRow(String tablename, String rowid) {

		LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();

		try {
			Connection connection = ConnectionFactory.createConnection(config.getconfig());
			Table table = connection.getTable(TableName.valueOf(tablename));
			Get get = new Get(rowid.getBytes());
			Result getResult = table.get(get);
			//			System.out.println(getResult);
			//			System.out.println(Bytes.toString(getResult.getValue("name".getBytes(), "fname".getBytes())));
			//			System.out.println(Bytes.toString(getResult.getValue("salary".getBytes(),"ctc".getBytes())));

			map.put("id",rowid);
			for(Cell cell : getResult.listCells()) {
				String qualifier = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
				String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
				System.out.println("Qualifier : " + qualifier
						+ " : Value : " + value);

				map.put(qualifier,value);
			}

		}catch(Exception e) {
			System.out.print(e);
		}
		return map;

	}

	@Override
	public void getColumnFamilies(String tablename) {

		try {
			for (String column : hbaseMetaData  .getColumns(tablename, 10000)) {
				System.out.println(tablename + "," + column);
			}

		}catch(Exception e) {
			System.out.print(e);
		}

	}

	@Override
	public void putRow(String tablename, HttpServletRequest request) {

		// Put Object for update also
		try {
			Connection connection = ConnectionFactory.createConnection(config.getconfig());
			Table table = connection.getTable(TableName.valueOf(tablename));
			
			String rowId = request.getParameter("rowid");
			String columnFamily = request.getParameter("cf");
			String columnFamilyValue = request.getParameter("cfvalue");
			
			String[] cf = columnFamily.split(":",2);
			
			Put put = new Put(rowId.getBytes());
			put.addColumn(cf.length>0?cf[0].getBytes():"".getBytes(), cf.length>1?cf[1].getBytes():"".getBytes(), columnFamilyValue.getBytes());
			table.put(put);
			
		}catch(Exception e) {
			System.out.print(e);
		}

	}

	@Override
	public void createTable(HttpServletRequest request) {

		// Put Object for creation 
		try {
			Connection connection = ConnectionFactory.createConnection(config.getconfig());
			
			String tablename = request.getParameter("tablename");
			String[] cf = request.getParameterValues("cf[]");

			TableDescriptorBuilder tableDescriptorBuilder =
				    TableDescriptorBuilder.newBuilder(TableName.valueOf(tablename));
			
			for (String column : cf) {
				ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder
					    .newBuilder(Bytes.toBytes(column)).build();
				tableDescriptorBuilder.addColumnFamily(columnFamilyDescriptor);
			}
			
			connection.getAdmin().createTable(tableDescriptorBuilder.build()); 
			
			
		}catch(Exception e) {
			System.out.print(e);
		}
		
	}

}
