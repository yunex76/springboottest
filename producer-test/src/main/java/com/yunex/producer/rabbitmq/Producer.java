package com.yunex.producer.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.yunex.producer.api.WebController;

@Component
public class Producer {

	private static final Logger log = LoggerFactory.getLogger(WebController.class);
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${jsa.rabbitmq.routingkey}")
	private String routingKey;
	
	public void produceMsg(String msg){
		amqpTemplate.convertAndSend(exchange, routingKey, msg);
		log.info("★★★★ Send msg = {}", msg);
	}
}
