package com.taikang.udp.mongo.query;

import org.springframework.data.mongodb.core.query.Update;

/**
 * 对MongoDB的Update进行封装
 * 
 * @author liuwei117
 * @version V1.0.0
 * @Credited 2015年10月19日 下午4:45:10
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class MongoUpdate {
	private Update update ;
	
	public MongoUpdate(){
		update = new Update();
	}

	/**
	 * 将字段KEY的值修改成value，如果字段不存在，则会创建字段
	 * @param key
	 * @param value
	 * @return
	 */
	public MongoUpdate set(String key, Object value){
		update.set(key, value);
		
		return this ;
	}
	
	/**
	 * 将字段KEY的值增加（或减少）inc，只能是整数、长整数或双精度浮点数
	 * 
	 * @param key
	 * @param inc
	 * @return
	 */
	public MongoUpdate inc(String key, Number inc){
		update.inc(key, inc);
		
		return this ;
	}
	
	public Update getUpdate() {
		return update;
	}
	
}
