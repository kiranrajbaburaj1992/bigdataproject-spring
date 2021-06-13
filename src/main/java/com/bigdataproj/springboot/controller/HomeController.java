package com.bigdataproj.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bigdataproj.springboot.model.TweetsUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

@Controller
public class HomeController {



	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView home() {
		System.setProperty("hadoop.home.dir", "C:\\winutil\\");
		Configuration conf = HBaseConfiguration.create();
		System.out.println("--------------+++++++++++\n");

		conf.set("hbase.master.port", "60001");
		conf.set("hbase.zookeeper.quorum", "hdoop-VirtualBox");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		conf.set("hbase.rootdir","hdfs://hdoop-VirtualBox:9000/hbase");
		conf.set("hbase.client.retries.number", "4");
		conf.set("hbase.rpc.timeout", "30000");
		conf.set("hbase.security.authentication", "simple");
		conf.set("zookeeper.znode.parent", "/hbase");
		conf.set("fs.defaultFS", "hdfs://hdoop-VirtualBox:9000");


		String result = "";
		List<TweetsUser> list = new ArrayList<TweetsUser>();
		try {
			Connection connection = ConnectionFactory.createConnection(conf);
			Table table = connection.getTable(TableName.valueOf("hbase_tweets_user"));
			Scan scan1 = new Scan();
			ResultScanner scanner1 = table.getScanner(scan1);

			for(Result res : scanner1) {
				Get get = new Get(res.getRow());
			    Result entireRow = table.get(get); 
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


		ModelAndView map = new ModelAndView("home.jsp");
		map.addObject("lists", list);
		return map;

	}

}




/*


		String result = "";
		List<String> list = new ArrayList<String>();
		try {
		Connection connection = ConnectionFactory.createConnection(conf);
		Table table = connection.getTable(TableName.valueOf("employee"));
		Scan scan1 = new Scan();
		ResultScanner scanner1 = table.getScanner(scan1);

		for(Result res : scanner1) {
			list.add(res.toString());
			System.out.println(Bytes.toString(res.getRow()));
			System.out.println(Bytes.toString(res.getValue("name".getBytes(), "fname".getBytes())));
			System.out.println(Bytes.toString(res.getValue("salary".getBytes(),"ctc".getBytes())));

		}
		scanner1.close();




		// Put Object for update also

		Put put = new Put("7".getBytes());

		put.addColumn("name".getBytes(), "fname".getBytes(), "value1".getBytes());
		put.addColumn("salary".getBytes(), "ctc".getBytes(), "value2".getBytes());

		table.put(put);


		// Get Object

		Get get = new Get("3".getBytes());
		Result getResult = table.get(get);
		System.out.println(getResult);
		System.out.println(Bytes.toString(getResult.getValue("name".getBytes(), "fname".getBytes())));
		System.out.println(Bytes.toString(getResult.getValue("salary".getBytes(),"ctc".getBytes())));


		//Delete Object
		Delete del = new Delete("1".getBytes());
		table.delete(del);

		list = new ArrayList<String>();
		scanner1 = table.getScanner(scan1);
		for(Result res : scanner1) {
			list.add(res.toString());
			System.out.println(Bytes.toString(res.getRow()));
			System.out.println(Bytes.toString(res.getValue("name".getBytes(), "fname".getBytes())));
			System.out.println(Bytes.toString(res.getValue("salary".getBytes(),"ctc".getBytes())));

		}

		scanner1.close();


		}catch(Exception e) {
			System.out.print(e);
		}

 */


