package com.bigdataproj.springboot.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;

public interface AppConfigService {
	
	public Configuration getconfig();

	Connection getConnection();

}
