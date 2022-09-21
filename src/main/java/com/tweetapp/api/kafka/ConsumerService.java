package com.tweetapp.api.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = "TweetMessage", groupId = "group_id")
    public void consume(String message) {
    	logger.info("Consumed message "+message);
        logger.info(String.format("Consumed message: %s", message));
    }
}