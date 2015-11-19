package com.taikang.udp.mongo.context;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.monitor.ServerInfo;
import org.springframework.stereotype.Repository;

import com.mongodb.CommandResult;
import com.mongodb.DBObject;
import com.taikang.udp.mongo.log.LoggerFactory;
import com.taikang.udp.mongo.vo.CollectionStats;
import com.taikang.udp.mongo.vo.DBStats;
import com.taikang.udp.mongo.vo.ServerStats;

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
    public ServerStats getServerStatus(){
    	try{
        	
    		ServerInfo si = new ServerInfo(mongoTemplate.getDb().getMongo());
    		CommandResult cr = si.getServerStatus() ;
        	
        	ServerStats serverStats = new ServerStats();
        	serverStats.populateCommandResult(cr);
        	
        	return serverStats;
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public DBStats getDBStatus(){
    	try{
        	
    		CommandResult cr = mongoTemplate.getDb().getStats();
    		
    		DBStats dbStats = new DBStats();
    		dbStats.populateCommandResult(cr);
    		
    		return dbStats ;
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public CollectionStats getCollectionStatus(String collectionName){
    	try{
        	
    		CommandResult cr = mongoTemplate.getDb().getCollection(collectionName).getStats();
    		
    		CollectionStats collectionStats = new CollectionStats();
    		collectionStats.populateCommandResult(cr);
    		
        	return collectionStats ;
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		e.printStackTrace();
    		return null;
    	}
    }
    
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}
