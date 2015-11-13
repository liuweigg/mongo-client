package com.taikang.udp.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taikang.udp.mongo.context.MongoDBClient;
import com.taikang.udp.mongo.context.MongoStatusDBClient;
import com.taikang.udp.mongo.log.LoggerFactory;
import com.taikang.udp.mongo.query.MongoCriteria;
import com.taikang.udp.mongo.query.MongoQuery;
import com.taikang.udp.mongo.query.MongoUpdate;
import com.taikang.udp.mongo.util.MongoUtils;

public class TestMongoStatusClient {

	public static final Logger logger = LoggerFactory.getLogger();
	
	@Test
	public void insert(){
		try{
			ApplicationContext context = new ClassPathXmlApplicationContext("mongo-config.xml");
			MongoStatusDBClient client = context.getBean(MongoStatusDBClient.class);
			
			client.getIndexInfo();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
