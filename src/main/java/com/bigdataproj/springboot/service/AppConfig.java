package com.bigdataproj.springboot.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
public class AppConfig implements AppConfigService {

	@Override
	public Configuration getconfig() {
		System.setProperty("hadoop.home.dir", "C:\\winutil\\");

		Configuration conf = HBaseConfiguration.create();

		conf.set("hbase.master.port", "60001");
		conf.set("hbase.zookeeper.quorum", "hdoop-VirtualBox");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		conf.set("hbase.rootdir","hdfs://hdoop-VirtualBox:9000/hbase");
		conf.set("hbase.client.retries.number", "4");
		conf.set("hbase.rpc.timeout", "30000");
		conf.set("hbase.security.authentication", "simple");
		conf.set("zookeeper.znode.parent", "/hbase");
		conf.set("fs.defaultFS", "hdfs://hdoop-VirtualBox:9000");
		
		return conf;
	}
}
