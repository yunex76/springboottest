package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.example.app.Frontend;

@EnableAutoConfiguration
@ComponentScan
public class HajibootDiApplication {

	public static void main(String[] args) {
		
		try (ConfigurableApplicationContext context =
				SpringApplication.run(HajibootDiApplication.class, args) ) {
			
			Frontend frontend = context.getBean(Frontend.class);
			frontend.run();
		}
	}
}
