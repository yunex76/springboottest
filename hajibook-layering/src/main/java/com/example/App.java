package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.example.domain.Customer;
import com.example.service.CustomerService;

@EnableAutoConfiguration
@ComponentScan
public class App implements CommandLineRunner {

	@Autowired
	CustomerService customerService;
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		// 데이터 추가
		customerService.save(new Customer(1, "nobita", "nobbi"));
		customerService.save(new Customer(2, "takeshi", "goda"));
		customerService.save(new Customer(3, "suneo", "honekawa"));
		
		// 데이터 표시
		customerService.findAll().forEach(System.out::println);
	}
}
