/*
 * Copyright (C) 2016 - SmartJob.vn
 * SmartJob VN proprietary/confidential. Use is subject to license terms.
 * Website: http://smartJob.vn
 * Email: contact@smartJob.vn
 * Telephone: (84)-4-6294 44 47
 */

package vn.smartJob.jobs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import com.mongodb.MongoClient;

/**
 * Configuration information.
 * 
 * @author SmartJob
 *
 */
@Configuration
public class MongoConfig {
	/**
	 * Ip server address. In this case, it is also localhost.
	 */
	private static final String SERVER_IP = "127.0.0.1";

	/**
	 * Database name.
	 */
	private static final String DATABASE_NAME = "smartjob";

	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		MongoClient serverIp = new MongoClient(SERVER_IP);
		return new SimpleMongoDbFactory(serverIp, DATABASE_NAME);
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

}
