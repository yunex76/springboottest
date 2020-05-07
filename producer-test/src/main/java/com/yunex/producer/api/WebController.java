package com.yunex.producer.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunex.producer.rabbitmq.Producer;

@RestController
public class WebController {

	private static final Logger log = LoggerFactory.getLogger(WebController.class);

	@Autowired
	Producer producer;
	
	@RequestMapping("/send")
	public String sendMsg() {
		log.info("★★★★ sendMsg() start!!");
		producer.produceMsg("hello world");
		return "Producer::Done";
	}
}
