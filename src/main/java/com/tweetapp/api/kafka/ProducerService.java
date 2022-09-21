package com.tweetapp.api.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService 
{
	public static final String topic = "TweetMessage";
	private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

	@Autowired
	private KafkaTemplate<String,String> kafkaTemp;
	
	public void sendMessage(String message)
	{
		logger.info("Publishing to topic "+ topic);
		this.kafkaTemp.send(topic,message);
	}
}