package com.tweetapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;



@SpringBootApplication
@EnableMongoAuditing
public class TweetApplication {

	
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(TweetApplication.class);
		logger.debug("===========> Tweet App Started <===========");
		SpringApplication.run(TweetApplication.class, args);
	}
}
