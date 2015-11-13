package com.taikang.udp.mongo.query;

import java.util.Collection;

import org.springframework.data.mongodb.core.query.Criteria;

import com.taikang.udp.mongo.util.MongoUtils;
import com.taikang.udp.mongo.util.PatternUtils;

/**
 * 对MongoDB的查询条件进行封装 
 * 
 * @author liuwei117
 * @version V1.0.0
 * @Credited 2015年10月19日 下午4:44:06
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class MongoCriteria extends Criteria{

	public MongoCriteria() { 
	}

	/**
	 * "="
	 * 
	 * @param key
	 * @param value
	 * @return Criteria
	 */
	public static Criteria is(String key , Object value) {
		return where(key).is(value) ;
	}

	/**
	 * "<"
	 * 
	 * @param key
	 * @param value
	 * @return Criteria
	 */
	public static Criteria lessThan(String key , Object value){
		return where(key).lt(value);
	}
	
	/**
	 * "<="
	 * 
	 * @param key
	 * @param value
	 * @return Criteria
	 */
	public static Criteria lessThanEqual(String key , Object value){
		return where(key).lte(value);
	}

	/**
	 * ">"
	 * 
	 * @param key
	 * @param value
	 * @return Criteria
	 */
	public static Criteria greaterThan(String key , Object value){
		return where(key).gt(value);
	}
	
	/**
	 * ">="
	 * 
	 * @param key
	 * @param value
	 * @return Criteria
	 */
	public static Criteria greaterThanEqual(String key , Object value){
		return where(key).gte(value);
	}

	/**
	 * "Between And"
	 * 
	 * @param key
	 * @param valueFrom
	 * @param valueto
	 * @return Criteria
	 */
	public static Criteria between(String key , Object valueFrom , Object valueto){
		return where(key).gte(valueFrom).lte(valueto);
	}
	
	/**
	 * "IN"
	 * 
	 * @param key
	 * @param c(集合)
	 * @return Criteria
	 */
	public static Criteria in(String key , Collection<?> collection){
		return where(key).in(collection);
	}
	
	/**
	 * "IN"
	 * 
	 * @param key
	 * @param Objects
	 * @return Criteria
	 */
	public static Criteria in(String key , Object... objects){
		return where(key).in(objects);
	}
	
	/**
	 * "NOT IN"
	 * 
	 * @param key
	 * @param c(集合)
	 * @return Criteria
	 */
	public static Criteria notIn(String key , Collection<?> collection){
		return where(key).nin(collection);
	}
	
	/**
	 * "NOT IN"
	 * 
	 * @param key
	 * @param Objects
	 * @return Criteria
	 */
	public static Criteria notIn(String key , Object... objects){
		return where(key).nin(objects);
	}
	
	/**
	 * "IS NULL"
	 * 
	 * @param key
	 * @return Criteria
	 */
	public static Criteria isNull(String key){
		return where(key).is(MongoUtils.NULL);
	}

	/**
	 * "IS NOT NULL"
	 * 
	 * @param key
	 * @return Criteria
	 */
	public static Criteria isNotNull(String key){
		return where(key).ne(MongoUtils.NULL);
	}
	
	/**
	 * "LIKE '%value%'"
	 * 
	 * @param key
	 * @param value
	 * @return Criteria
	 */
	public static Criteria like(String key , String value){
		return where(key).is(PatternUtils.Like(value)) ;
	}
	
	/**
	 * "LIKE 'value%'"
	 * 
	 * @param key
	 * @param value
	 * @return Criteria
	 */
	public static Criteria startWith(String key , String value){
		return where(key).is(PatternUtils.StartWith(value)) ;
	}
	
	/**
	 * "LIKE '%value'"
	 * 
	 * @param key
	 * @param value
	 * @return Criteria
	 */
	public static Criteria endWith(String key , String value){
		return where(key).is(PatternUtils.EndWith(value)) ;
	}
	
	/**
	 * 通过正则表达式pattern进行匹配
	 * 
	 * @param key
	 * @param pattern
	 * @return
	 */
	public static Criteria likeByRegex(String key , String pattern){
		return where(key).regex(pattern);
	}

	/**
	 * 或者
	 * "or"
	 * 
	 * @param criteria
	 * @return
	 */
	public static MongoCriteria or(Criteria... criteria){
		MongoCriteria cra = new MongoCriteria();
		cra.orOperator(criteria);
		//query.addCriteria(cra);
		
		return cra;
	}
	
	/**
	 * 并且
	 * "and"
	 * 
	 * @param criteria
	 * @return
	 */
	public static MongoCriteria and(Criteria... criteria){
		MongoCriteria cra = new MongoCriteria();
		cra.andOperator(criteria);
		//query.addCriteria(cra);
		
		return cra;
	}


	
	/**
	 *  Keyword							Sample										Logical result
	 *  
	 *  After							findByBirthdateAfter(Date date)				{"birthdate" : {"$gt" : date}}
	 *  GreaterThan						findByAgeGreaterThan(int age)				{"age" : {"$gt" : age}}
	 *  GreaterThanEqual				findByAgeGreaterThanEqual(int age)			{"age" : {"$gte" : age}}
	 *  Before							findByBirthdateBefore(Date date)			{"birthdate" : {"$lt" : date}}
	 *  LessThan						findByAgeLessThan(int age)					{"age" : {"$lt" : age}}
	 *  LessThanEqual					findByAgeLessThanEqual(int age)				{"age" : {"$lte" : age}}
	 *  Between							findByAgeBetween(int from, int to)			{"age" : {"$gt" : from, "$lt" : to}}
	 *  In								findByAgeIn(Collection ages)				{"age" : {"$in" : [ages…​]}}
	 *  NotIn							findByAgeNotIn(Collection ages)				{"age" : {"$nin" : [ages…​]}}
	 *  IsNotNull, NotNull				findByFirstnameNotNull()					{"firstname" : {"$ne" : null}}
	 *  IsNull, Null					findByFirstnameNull()						{"firstname" : null}
	 *  Like, StartingWith, EndingWith	findByFirstnameLike(String name)			{"firstname" : name} ( name as regex)
	 *  Containing on String			findByFirstnameContaining(String name)		{"firstname" : name} (name as regex)
	 *  Containing on Collection		findByAddressesContaining(Address address)	{"addresses" : { "$in" : address}}
	 *  Regex							findByFirstnameRegex(String firstname)		{"firstname" : {"$regex" : firstname }}
	 *  (No keyword)					findByFirstname(String name)				{"firstname" : name}
	 *  Not								findByFirstnameNot(String name)				{"firstname" : {"$ne" : name}}
	 *  Near							findByLocationNear(Point point)				{"location" : {"$near" : [x,y]}}
	 *  Near							findByLocationNear(Point point, Distance max)					{"location" : {"$near" : [x,y], "$maxDistance" : max}}
	 *  Near							findByLocationNear(Point point, Distance min, Distance max)		{"location" : {"$near" : [x,y], "$minDistance" : min, "$maxDistance" : max}}
	 *  Within							findByLocationWithin(Circle circle)			{"location" : {"$geoWithin" : {"$center" : [ [x, y], distance]}}}
	 *  Within							findByLocationWithin(Box box)				{"location" : {"$geoWithin" : {"$box" : [ [x1, y1], x2, y2]}}}
	 *  IsTrue, True					findByActiveIsTrue()						{"active" : true}
	 *  IsFalse, False					findByActiveIsFalse()						{"active" : false}
	 *  Exists							findByLocationExists(boolean exists)		{"location" : {"$exists" : exists }}
		
		
	 * @param key
	 * @param value
	 * @return
	 */
}
