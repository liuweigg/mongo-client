package com.taikang.udp.mongo.context;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.monitor.ServerInfo;

import static com.taikang.udp.mongo.query.MongoCriteria.*;

import org.springframework.stereotype.Repository;

import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.ReadPreference;
import com.mongodb.WriteResult;
import com.taikang.udp.mongo.User;
import com.taikang.udp.mongo.UserCount;
import com.taikang.udp.mongo.log.LoggerFactory;
import com.taikang.udp.mongo.query.MongoAggregation;
import com.taikang.udp.mongo.query.MongoQuery;
import com.taikang.udp.mongo.query.MongoUpdate;

/**
 * 
 * 〈功能简述〉
 * @author liuwei117
 * @version [版本号，默认V1.0.0]
 * @Credited 2015年11月4日 上午9:43:45
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Repository
public class MongoStatusDBClient<T> {

	public static final Logger logger = LoggerFactory.getLogger();
      
    /** 
     * spring mongodb　集成操作类　 
     */  
    @Autowired
	MongoTemplate mongoTemplate;
  
    /**
     * 
     */
    public void getStatus(){
    	try{
        	
    		ServerInfo si = new ServerInfo(mongoTemplate.getDb().getMongo());
    		
        	System.out.println("cr.toString() = " + si.getServerStatus());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void getDBStatus(){
    	try{
        	
    		CommandResult cr = mongoTemplate.getDb().getStats();
    		
        	System.out.println("cr.toString() = " + cr.toString());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void getCollectionStatus(){
    	try{
        	
    		CommandResult cr = mongoTemplate.getDb().getCollection("user").getStats();
    		
        	System.out.println("cr.toString() = " + cr.toString());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void getIndexInfo(){
    	try{
        	
    		List<DBObject> cr = mongoTemplate.getDb().getCollection("user").getIndexInfo();
    		
    		for(DBObject ob : cr){
    			System.out.println("cr.toString() = " + ob.toString());
    		}
        	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
   
    public void getCollections(){
    	
    }
    
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}
