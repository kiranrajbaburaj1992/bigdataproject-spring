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
		

		//conf.set("hbase.master", "192.168.43.183:60001");
//		conf.set("hbase.master.port", "60000");
//		conf.set("hbase.zookeeper.quorum", "quickstart.cloudera");
//		conf.set("hbase.zookeeper.property.clientPort", "2181");
//		conf.set("hbase.rootdir","hdfs://quickstart.cloudera:8020/hbase");
//		conf.set("hbase.client.retries.number", "4");
//		conf.set("hbase.rpc.timeout", "30000");
//		conf.set("hbase.security.authentication", "simple");
//        conf.set("zookeeper.znode.parent", "/hbase");
//		conf.set("fs.defaultFS", "hdfs://quickstart.cloudera:8020");
		
		
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
		List<String> list = new ArrayList<String>();
		try {
		Connection connection = ConnectionFactory.createConnection(conf);
		Table table = connection.getTable(TableName.valueOf("employee"));
		Scan scan1 = new Scan();
		ResultScanner scanner1 = table.getScanner(scan1);
		
//		for(Result res : scanner1) {
//			list.add(res.toString());
//			System.out.println(Bytes.toString(res.getRow()));
//			System.out.println(Bytes.toString(res.getValue("personal".getBytes(), "name".getBytes())));
//			System.out.println(Bytes.toString(res.getValue("personal".getBytes(),"salary".getBytes())));
//
//		}
		scanner1.close();
//		
//		
//		
//
//		// Put Object for update also
//		
//		Put put = new Put("7".getBytes());
//
//		put.addColumn("personal".getBytes(), "name".getBytes(), "value1".getBytes());
//		put.addColumn("personal".getBytes(), "salary".getBytes(), "value2".getBytes());
//		
//		table.put(put);
//		
//		
//		// Get Object
//		
		Get get = new Get("3".getBytes());
		Result getResult = table.get(get);
		System.out.println(getResult);
//		System.out.println(Bytes.toString(getResult.getValue("personal".getBytes(), "name".getBytes())));
//		System.out.println(Bytes.toString(getResult.getValue("personal".getBytes(),"salary".getBytes())));
//		
//		
//		//Delete Object
//		Delete del = new Delete("1".getBytes());
//		table.delete(del);
//		
//		list = new ArrayList<String>();
//		scanner1 = table.getScanner(scan1);
//		for(Result res : scanner1) {
//			list.add(res.toString());
//			System.out.println(Bytes.toString(res.getRow()));
//			System.out.println(Bytes.toString(res.getValue("personal".getBytes(), "name".getBytes())));
//			System.out.println(Bytes.toString(res.getValue("personal".getBytes(),"salary".getBytes())));
//
//		}
//		
//		scanner1.close();
//		
//		
		}catch(Exception e) {
			System.out.print(e);
		}
		
		
		
		
		ModelAndView map = new ModelAndView("home.jsp");
	    //map.addObject("lists", list);
	    return map;
		
	}

}




/*
 * 
 * hbase-site-env.xml
 *  
<configuration>
<property>
<name>hbase.rootdir</name>
<value>hdfs://hdoop-VirtualBox:9000/hbase</value>
</property>

<property>
<name>hbase.master.port</name>
<value>60001</value>
</property>

<property>
<name>hbase.cluster.distributed</name>
<value>true</value>
</property>

<property>
<name>hbase.zookeeper.property.dataDir</name>
<value>/home/hdoop/zookeeper</value>
</property>

<property>
<name>hbase.zookeeper.property.maxClientCnxns</name>
<value>35</value>
</property>

<property>
<name>hbase.zookeeper.quorum</name>
<value>hdoop-VirtualBox</value>
</property>

<property>
<name>hbase.zookeeper.property.clientPort</name>
<value>2181</value>
</property>

<property>
<name>hbase.unsafe.stream.capability.enforce</name>
<value>false</value>
</property>

<property>
<name>hbase.regionserver.ipc.address</name>
<value>0.0.0.0</value>
</property>

<property>
<name>hbase.master.ipc.address</name>
<value>0.0.0.0</value>
</property>
</configuration>
 
 
 * 
 * 
 * 
 */
