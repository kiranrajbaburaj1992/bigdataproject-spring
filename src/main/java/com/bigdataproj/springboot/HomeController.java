package com.bigdataproj.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
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
		
		conf.set("hbase.zookeeper.quorum", "quickstart.cloudera");
		conf.set("hbase.zookeeper.property.clientPort", "2181");

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
			System.out.println(Bytes.toString(res.getValue("personal".getBytes(), "name".getBytes())));
			System.out.println(Bytes.toString(res.getValue("personal".getBytes(),"salary".getBytes())));

		}
		scanner1.close();
		
		
		

		// Put Object for update also
		
		Put put = new Put("7".getBytes());

		put.addColumn("personal".getBytes(), "name".getBytes(), "value1".getBytes());
		put.addColumn("personal".getBytes(), "salary".getBytes(), "value2".getBytes());
		
		table.put(put);
		
		
		// Get Object
		
		Get get = new Get("3".getBytes());
		Result getResult = table.get(get);
		System.out.println(getResult);
		System.out.println(Bytes.toString(getResult.getValue("personal".getBytes(), "name".getBytes())));
		System.out.println(Bytes.toString(getResult.getValue("personal".getBytes(),"salary".getBytes())));
		
		
		//Delete Object
		Delete del = new Delete("1".getBytes());
		table.delete(del);
		
		list = new ArrayList<String>();
		scanner1 = table.getScanner(scan1);
		for(Result res : scanner1) {
			list.add(res.toString());
			System.out.println(Bytes.toString(res.getRow()));
			System.out.println(Bytes.toString(res.getValue("personal".getBytes(), "name".getBytes())));
			System.out.println(Bytes.toString(res.getValue("personal".getBytes(),"salary".getBytes())));

		}
		
		scanner1.close();
		
		
		}catch(Exception e) {
			
		}
		
		
		
		
		ModelAndView map = new ModelAndView("home.jsp");
	    map.addObject("lists", list);
	    return map;
		
	}

}
