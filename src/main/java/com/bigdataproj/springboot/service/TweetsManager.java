package com.bigdataproj.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigdataproj.springboot.model.TweetsUser;

@Service
public class TweetsManager implements TweetsService {

	@Autowired
	AppConfig config;

	@Override
	public List<TweetsUser> getTweetsUsers() {

		List<TweetsUser> list = new ArrayList<TweetsUser>();
		try {
			Connection connection = ConnectionFactory.createConnection(config.getconfig());
			Table table = connection.getTable(TableName.valueOf("hbase_tweets_user"));
			Scan scan1 = new Scan();
			ResultScanner scanner1 = table.getScanner(scan1);

			for(Result res : scanner1) {
				//Get get = new Get(res.getRow());
				//Result entireRow = table.get(get); 
				//System.out.println(entireRow);

				TweetsUser tweetsUser = new TweetsUser();
				tweetsUser.setId(Bytes.toString(res.getRow()));
				tweetsUser.setScreen_name(Bytes.toString(res.getValue("cf".getBytes(),"screen_name".getBytes())));
				tweetsUser.setName(Bytes.toString(res.getValue("cf".getBytes(),"name".getBytes())));
				tweetsUser.setFriends_count(Bytes.toString(res.getValue("cf".getBytes(),"friends_count".getBytes())));
				tweetsUser.setFollowers_count(Bytes.toString(res.getValue("cf".getBytes(),"followers_count".getBytes())));
				tweetsUser.setStatuses_count(Bytes.toString(res.getValue("cf".getBytes(),"statuses_count".getBytes())));
				tweetsUser.setVerified(Bytes.toString(res.getValue("cf".getBytes(),"verified".getBytes())));
				tweetsUser.setUtc_offset(Bytes.toString(res.getValue("cf".getBytes(),"utc_offset".getBytes())));
				tweetsUser.setTime_zone(Bytes.toString(res.getValue("cf".getBytes(),"time_zone".getBytes())));
				System.out.println(Bytes.toString(res.getValue("cf".getBytes(),"screen_name".getBytes())));

				list.add(tweetsUser);
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


}
