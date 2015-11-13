package com.taikang.udp.mongo.query;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.taikang.udp.mongo.util.MongoUtils;

/** 
 * MongoDB 查询语句SQL语句的封装 
 * 其中包括：
 * 查询条件	 List<Criteria>
 * 排序 		 Sort
 * 分页		 Query.limit/skip
 * 
 * 在Query的基础上进行封装，若查询涉及到了分页操作，需要将Query转成BasicQuery
 * Query与Criteria的区别：Query中已经包含where条件，Criteria相当于是一个条件，where条件中可以包含0个、1个或多个Criteria
 * 举例：
 * ....where name = 'user_name' and age > 30 
 * 其中< name = 'user_name' > 相当于一个Criteria , < age > 30 >相当于另一个Criteria
 * 这两个Criteria 加上 'and' 条件组成了一个Query中的where语句
 * 
 * @author liuwei117 
 * @version V1.0.0
 * @Credited 2015年10月19日 下午4:44:37
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class MongoQuery /* extends BasicMongoQuery*/{
	private MongoCriteria mongoCriteria ;
	private Query query ;
	private Sort sort ;
	private DBObject fieldObject ;
	//private int page = -1;
	//private int size = -1;

	public MongoQuery() {
		query = new Query();
		mongoCriteria = new MongoCriteria();
	}
	
	public MongoQuery(CriteriaDefinition criteriaDefinition) {
		query = new Query(criteriaDefinition);
		mongoCriteria = new MongoCriteria();
	}
	
	
	
	/**
	 * Adds the given {@link CriteriaDefinition} to the current {@link Query}.
	 * 
	 * @param criteriaDefinition must not be {@literal null}.
	 * @return
	 * @since 1.6
	 */
	public MongoQuery addCriteria(CriteriaDefinition criteriaDefinition) {
		query.addCriteria(criteriaDefinition);
		
		return this;
	}
	
	/**
	 * Adds the given {@link CriteriaDefinition} to the current {@link Query}.
	 * 
	 * @param criteriaDefinition must not be {@literal null}.
	 * @return
	 * @since 1.6
	 */
	public MongoQuery add(CriteriaDefinition criteriaDefinition) {
		query.addCriteria(criteriaDefinition);
		
		return this;
	}
	
	/**
	 * 设置排序条件
	 * setSort(MongoUtils.ASC , "properties1" , "properties2") = order by properties1 , properties2 asc
	 * 
	 * @param direction 升序:MongoUtils.ASC;降序:MongoUtils.DESC
	 * @param properties 排序的字段
	 * @return
	 */
	public MongoQuery setSort(String direction, String... properties){
		query.with(new Sort(MongoUtils.getOrder(direction) , properties)); 
		return this;
	}
	
	/**
	 * 设置返回的字段（也可以设置不返回哪些字段）
	 * 
	 * @param isReturn TRUE：表示返回字段，FALSE表示不返回字段（其余字段全部返回）
	 * @param properties 字段
	 * @return
	 */
	public void setFieldObject(boolean isReturn, String... properties){
		if(this.fieldObject == null){
			this.fieldObject = new BasicDBObject();
		}
		for(String property : properties){
			this.fieldObject.put(property, isReturn);
		}
	}
	
	/**
	 * 设置页数和每页的数据量
	 * @param page
	 * @param pageSize
	 */
	public void setPage(int page, int pageSize){
		this.query.skip(page);
		this.query.limit(pageSize);
	}
	
	private DBObject getQueryObject(){
		return query.getQueryObject();
	}
	
	public Query getQuery() {
		
		
		if(this.fieldObject == null){
			return query;
		}else{
			BasicQuery basicQuery = new BasicQuery(this.getQueryObject() , fieldObject);
			basicQuery.setSortObject(this.query.getSortObject());
			basicQuery.skip(this.query.getSkip());
			basicQuery.limit(this.query.getLimit());
			
			return  basicQuery ;
		}
		
	}
	
}
