package com.taikang.udp.mongo;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import com.taikang.udp.framework.common.json.JsonMapper;
import com.taikang.udp.mongo.context.MongoDBClient;
import com.taikang.udp.mongo.log.LoggerFactory;
import com.taikang.udp.mongo.query.MongoAggregation;
import com.taikang.udp.mongo.query.MongoCriteria;
import com.taikang.udp.mongo.util.MongoUtils;


public class TestMapreduce {
public static final Logger logger = LoggerFactory.getLogger();
	
	@Test
	public void groupBy(){
		ApplicationContext context = new ClassPathXmlApplicationContext("mongo-config.xml");
		MongoDBClient client = context.getBean(MongoDBClient.class);
		try{
		MongoAggregation ma = new MongoAggregation();

		ma.setGroup("company","height");
		//ma.setGroup("height");
		
		ma.addMatch(MongoCriteria.greaterThan("age", 20));
		ma.addMatch(MongoCriteria.lessThan("age", 40));
		
		ma.count("userCount");
		ma.sum("age", "ageSum");
		ma.max("age", "maxAge");
		ma.min("age", "minAge");
		ma.first("name", "firstName");
		ma.last("name", "lastName");
		ma.avg("age", "avgAge");  
		
		ma.addSort(MongoUtils.ASC, "company");
		
		ma.setReturnField("column");
		
		List<UserCount> result = client.group(ma, "user", UserCount.class);
		
		/*MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);
		
		Aggregation agg = newAggregation(
			      match(Criteria.where("age").gt(20)),
			      match(Criteria.where("age").lt(40)),
			      group("company","height").count().as("userCount")
			      	.sum("age").as("ageSum")
			      	.max("age").as("maxAge")
			      	.min("age").as("minAge")
			      	.first("name").as("firstName")
			      	.last("name").as("lastName")
			      	.avg("age").as("avgAge"),
			      project("userCount","ageSum","maxAge","minAge","firstName","lastName","avgAge").and("company").previousOperation(),
			      sort(MongoUtils.getOrder(MongoUtils.ASC), "company")
				 );
		
		 AggregationResults<UserCount> groupResults 
	      	= mongoTemplate.aggregate(agg, User.class, UserCount.class);
	     List<UserCount> result = groupResults.getMappedResults();*/
	     
	     if(result != null && result.size() > 0){
	    	 for(UserCount uc : result){
	    		 if(uc != null){
	    			 JsonMapper jm = new JsonMapper();
	    			 //uc = jm.update(uc.getColumn(), uc) ;
	    			 
	    			 System.out.println("Company = " + uc.getCompany() + " | Height = " + uc.getHeight() + " | Count =" + uc.getUserCount() 
	    					 + " | AgeSum = " + uc.getAgeSum()
	    					 + " | MaxAge = " + uc.getMaxAge()
	    					 + " | MinAge = " + uc.getMinAge()
	    					 + " | FirstName = " + uc.getFirstName()
	    					 + " | LastName = " + uc.getLastName()
	    					 + " | AvgAge = " + uc.getAvgAge()
	    					 + " | Column = " + uc.getColumn()) ;
	    		 }
	    	 }
	     }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
