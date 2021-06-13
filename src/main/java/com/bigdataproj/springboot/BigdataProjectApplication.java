package com.bigdataproj.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BigdataProjectApplication  extends SpringBootServletInitializer {
	
	public static void main(String[] args) {

		SpringApplication.run(BigdataProjectApplication.class, args);
	}

}
