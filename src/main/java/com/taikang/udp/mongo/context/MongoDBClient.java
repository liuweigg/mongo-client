package com.taikang.udp.mongo.context;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import static com.taikang.udp.mongo.query.MongoCriteria.*;

import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;
import com.taikang.udp.mongo.User;
import com.taikang.udp.mongo.UserCount;
import com.taikang.udp.mongo.log.LoggerFactory;
import com.taikang.udp.mongo.query.MongoAggregation;
import com.taikang.udp.mongo.query.MongoQuery;
import com.taikang.udp.mongo.query.MongoUpdate;

/**
 * 客户端操作类
 * 
 * @author liuwei117
 * @version V1.0.0
 * @Credited 2015年10月19日 下午4:43:35
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Repository
public class MongoDBClient<T> {

	public static final Logger logger = LoggerFactory.getLogger();
      
    /** 
     * spring mongodb　集成操作类　 
     */  
    @Autowired
	MongoTemplate mongoTemplate;
  
    /**
     * 保存一个对象到数据库中
     * 集合名称默认等于类名称，若需要指定集合名称，则需要使用public boolean insert(Object entity , String collectionName)方法
     * @param entity
     * @return
     */
    public boolean insert(Object entity) { 
    	try {
    		//mongoTemplate.setWriteConcern(WriteConcern.SAFE);
    		mongoTemplate.insert(entity);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
    }

    /**
     * 保存一个对象到指定的集合(collectionName)中
     * @param entity
     * @param collectionName
     * @return
     */
    public boolean insert(Object entity , String collectionName) { 
    	try {
    		//mongoTemplate.setWriteConcern(WriteConcern.SAFE);
    		mongoTemplate.insert(entity, collectionName);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
    }
    
    /**
     * 保存一个Map到指定的集合(collectionName)中
     * @param map
     * @param collectionName
     * @return
     */
    public boolean insertMap(Map map , String collectionName) {  
    	try {
	        mongoTemplate.insert(map , collectionName); 
	        return true;  
        } catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
    }
    
    public boolean insertList(List list , String collectionName) {  
    	try {
	        mongoTemplate.insert(list, collectionName);
	        return true;  
        } catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
    }
    
    public boolean insertList(List list) {  
    	try {
	        mongoTemplate.insertAll(list);
	        return true;  
        } catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
    }
    
    public boolean insertList(List list , Class entityClazz) {  
    	try {
	        mongoTemplate.insert(list, entityClazz);
	        return true;  
        } catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
    }
    
    /**
     * 查询一条数据，若查询结果为多条，只返回第一条数据
     * 查询条件为：where key1 = value1 and key2 = value2 ... 
     * 集合名称默认等于类名称
     * 
     * @param map
     * @param entityClass
     * @return Object
     */
    public Object findOne(Map<String, Object> map , Class entityClazz){
    	try {
    		return this.findOne(this.formatQuery(map) , entityClazz);
        } catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 查询一条数据，若查询结果为多条，只返回第一条数据
     * 查询条件为：where key1 = value1 and key2 = value2 ...
     * 
     * @param map
     * @param entityClazz
     * @param tableName
     * @return
     */
    public Object findOne(Map<String, Object> map , Class entityClazz , String tableName){
    	try {
    		return this.findOne(this.formatQuery(map) , entityClazz , tableName);
        } catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 查询一条数据，若查询结果为多条，只返回第一条数据
     * 查询条件为MongoQuery
     * 集合名称默认等于类名称
     * 
     * @param query
     * @param entityClazz
     * @return
     */
    public Object findOne(MongoQuery query, Class<T> entityClazz) {
    	try {
    		return mongoTemplate.findOne(query.getQuery(), entityClazz);
    	} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 查询一条数据，若查询结果为多条，只返回第一条数据
     * 查询条件为MongoQuery
     * 
     * @param query
     * @param entityClazz
     * @param tableName
     * @return
     */
    public Object findOne(MongoQuery query, Class<T> entityClazz, String tableName) {
    	try {
    		return mongoTemplate.findOne(query.getQuery(), entityClazz, tableName);
    	} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 查询多条数据
     * 查询条件为：where key1 = value1 and key2 = value2 ... 
     * 集合名称默认等于类名称
     * 
     * @param map
     * @param entityClass
     * @return Object
     */
    public List find(Map<String, Object> map , Class entityClazz){
    	try {
    		return this.find(this.formatQuery(map) , entityClazz);
        } catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 查询多条数据
     * 查询条件为：where key1 = value1 and key2 = value2 ...
     * 
     * @param map
     * @param entityClazz
     * @param tableName
     * @return
     */
    public List find(Map<String, Object> map , Class entityClazz , String tableName){
    	try {
    		return this.find(this.formatQuery(map) , entityClazz , tableName);
        } catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 查询多条数据
     * 查询条件为MongoQuery
     * 集合名称默认等于类名称
     * 
     * @param query
     * @param entityClazz
     * @return
     */
    public List find(MongoQuery query, Class<T> entityClazz) {
    	try {
    		return mongoTemplate.find(query.getQuery(), entityClazz);
    	} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 查询多条数据
     * 查询条件为MongoQuery
     * 
     * @param query
     * @param entityClazz
     * @param tableName
     * @return
     */
    public List find(MongoQuery query, Class<T> entityClazz, String tableName) {
    	try {
    		return mongoTemplate.find(query.getQuery(), entityClazz, tableName);
    	} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		}
    }
    
    
    /**
     * 统计数量
     * 
     * @param query
     * @param entityClazz
     * @return
     */
    public long count(MongoQuery query , Class<T> entityClazz){
    	try {
    		return mongoTemplate.count(query.getQuery(), entityClazz);
    	} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return 0;
		}
    }
    
    /**
     * 统计数量
     * 
     * @param map
     * @param entityClazz
     * @return
     */
    public long count(Map<String, Object> map , Class<T> entityClazz){
    	try {
    		return this.count( this.formatQuery(map) , entityClazz);
        } catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return 0;
		}
    }
    
    /**
     * 统计数量
     * 
     * @param query
     * @param tableName
     * @return
     */
    public long count(MongoQuery query , String tableName){
    	try {
    		return mongoTemplate.count(query.getQuery(), tableName );
    	} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return 0;
		}
    }
    
    /**
     * 统计数量
     * 
     * @param map
     * @param tableName
     * @return
     */
    public long count(Map<String, Object> map , String tableName){
    	try {
    		return this.count(this.formatQuery(map) , tableName);
        } catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return 0;
		}
    }
    
    /**
     * 更新操作
     * 
     * @param query
     * @param update
     * @param entityClazz
     * @return
     */
    public int update(MongoQuery query, MongoUpdate update, Class entityClazz){
    	try {
    		WriteResult writeResult = mongoTemplate.updateMulti(query.getQuery(), update.getUpdate() , entityClazz);
    		
    		if(writeResult != null){
    			return writeResult.getN() ;
    		}else{
    			return 0 ;
    		}
    		
    	}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return 0 ;
		}
    }
    
    /**
     * 更新操作
     * 
     * @param query
     * @param update
     * @param tableName
     * @return
     */
    public int update(MongoQuery query, MongoUpdate update, String tableName){
    	try {
    		WriteResult writeResult = mongoTemplate.updateMulti(query.getQuery(), update.getUpdate() , tableName);
    		
    		if(writeResult != null){
    			return writeResult.getN() ;
    		}else{
    			return 0 ;
    		}
    		
    	}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return 0 ;
		}
    }
    
    /**
     * 更新操作
     * 
     * @param map
     * @param update
     * @param entityClazz
     * @return
     */
    public int update(Map<String, Object> map, MongoUpdate update, Class entityClazz){
    	try {
    		return this.update(this.formatQuery(map) , update , entityClazz);
    	}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return 0 ;
		}
    }
    
    /**
     * 更新操作
     * 
     * @param map
     * @param update
     * @param tableName
     * @return
     */
    public int update(Map<String, Object> map, MongoUpdate update, String tableName){
    	try {
    		return this.update(this.formatQuery(map) , update , tableName);
    	}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return 0 ;
		}
    }
    
    /**
     * 删除操作
     * 
     * @param object
     * @return
     */
    public int delete(Object object){
    	try {
    		WriteResult writeResult = mongoTemplate.remove(object);
    		
    		if(writeResult != null){
    			return writeResult.getN() ;
    		}else{
    			return 0 ;
    		}
    		
    	}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return 0 ;
		}
    }
    
    /**
     * 删除操作
     * 
     * @param query
     * @param entityClazz
     * @return
     */
    public int delete(MongoQuery query , Class entityClazz){
    	try {
    		WriteResult writeResult = mongoTemplate.remove(query.getQuery() , entityClazz);
    		
    		if(writeResult != null){
    			return writeResult.getN() ;
    		}else{
    			return 0 ;
    		}
    		
    	}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return 0 ;
		}
    
    }
    
    /**
     * 删除操作
     * 
     * @param map
     * @param entityClazz
     * @return
     */
    public int delete(Map<String, Object> map , Class entityClazz){
    	try {
    		return this.delete(this.formatQuery(map) , entityClazz);
    	}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return 0 ;
		}
    
    }
    
    /**
     * 删除操作
     * 
     * @param query
     * @param tableName
     * @return
     */
    public int delete(MongoQuery query , String tableName){
    	try {
    		WriteResult writeResult = mongoTemplate.remove(query, tableName) ;
    		
    		if(writeResult != null){
    			return writeResult.getN() ;
    		}else{
    			return 0 ;
    		}
    		
    	}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return 0 ;
		}
    }
    
    /**
     * 删除操作
     * 
     * @param map
     * @param tableName
     * @return
     */
    public int delete(Map<String, Object> map , String tableName){
    	try {
    		return this.delete(this.formatQuery(map) , tableName);
    	}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return 0 ;
		}
    
    }
    
    /**
     * select distinct <key> from <tableName> where ....
     * 
     * @param key = "column1,column2,column3..."
     * @param query
     * @param tableName
     * @return
     */
    public List distinctFind(String key , MongoQuery query , String tableName){
    	try {
    		return mongoTemplate.getCollection(tableName).distinct(key, query.getQuery().getQueryObject());
    	}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null ;
		}
    }
    
    public List group(MongoAggregation ma , String tableName , Class resultClazz){
    	try {
    		 Aggregation agg = ma.formatAggregation();

    		 AggregationResults<UserCount> groupResults 
	 	      	= mongoTemplate.aggregate(agg, tableName , resultClazz);
	 	     return groupResults.getMappedResults();
    	}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null ;
		}
    }
    public List group(MongoAggregation ma , Class clazz , Class resultClazz){
    	try {
    		Aggregation agg = ma.formatAggregation();

    		 AggregationResults<UserCount> groupResults 
	 	      	= mongoTemplate.aggregate(agg, clazz , resultClazz);
	 	     return groupResults.getMappedResults();
    	}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return null ;
		}
    }
    
    /**
     * 将map转化成MongoQuery
     * key，value 将转换成 where key = value and key1 = value1 ...
     * 
     * 
     * @param map
     * @return
     */
    private MongoQuery formatQuery(Map<String, Object> map){
    	MongoQuery query = new MongoQuery();
		
		for(Map.Entry<String, Object> entry : map.entrySet()){
			if(entry != null 
					&& entry.getKey() != null 
					&& !"".equals(entry.getKey())){
				query.addCriteria(is(entry.getKey() , entry.getValue()));
			}
		}
		
		return query ;
    }
    
    
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}
