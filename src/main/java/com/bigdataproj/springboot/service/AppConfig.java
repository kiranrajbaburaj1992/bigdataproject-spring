package com.bigdataproj.springboot.service;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
public class AppConfig implements AppConfigService {
	
	private Connection connection;
	private Configuration conf;
	
	public AppConfig() throws IOException {

		System.setProperty("hadoop.home.dir", "C:\\winutil\\");

		this.conf = HBaseConfiguration.create();

		conf.set("hbase.master.port", "60001");
		conf.set("hbase.zookeeper.quorum", "hdoop-VirtualBox");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		conf.set("hbase.rootdir","hdfs://hdoop-VirtualBox:9000/hbase");
		conf.set("hbase.client.retries.number", "4");
		conf.set("hbase.rpc.timeout", "30000");
		conf.set("hbase.security.authentication", "simple");
		conf.set("zookeeper.znode.parent", "/hbase");
		conf.set("fs.defaultFS", "hdfs://hdoop-VirtualBox:9000");
		
		this.connection = ConnectionFactory.createConnection(conf);
	}

	@Override
	public Configuration getconfig() {
		
		return conf;
	}
	
	@Override
	public Connection getConnection() {
		return connection;
	}
}
