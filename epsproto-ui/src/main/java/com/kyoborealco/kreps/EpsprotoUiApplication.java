package com.kyoborealco.kreps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EpsprotoUiApplication extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(EpsprotoUiApplication.class);
	
	public static void main(String[] args) {
		log.info("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
    	log.info("★★★★★  RUN EpsprotoUiApplication  ★★★★★★★★★★");
    	log.info("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
    	
		SpringApplication.run(EpsprotoUiApplication.class, args);
	}

	@Override 
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) { 
		return builder.sources(EpsprotoUiApplication.class); 
	}

}
