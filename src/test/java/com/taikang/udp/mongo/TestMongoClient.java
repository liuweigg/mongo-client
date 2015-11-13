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
import com.taikang.udp.mongo.log.LoggerFactory;
import com.taikang.udp.mongo.query.MongoCriteria;
import com.taikang.udp.mongo.query.MongoQuery;
import com.taikang.udp.mongo.query.MongoUpdate;
import com.taikang.udp.mongo.util.MongoUtils;

public class TestMongoClient {

	public static final Logger logger = LoggerFactory.getLogger();
	
	@Test
	public void insert(){
		ApplicationContext context = new ClassPathXmlApplicationContext("mongo-config.xml");
		MongoDBClient client = context.getBean(MongoDBClient.class);
		
		//1.支持对象的插入
		User user = new User();
		user.setName("WIKI");
		user.setAge(15);
		user.setCompany("IBM");
		user.setHeight(180);
		user.setWeight(100);user.setAa("1111");
		
		//1.1直接执行insert(Object)，此时集合的名字和对象名字一致
		client.insert(user);
		
		//1.2也可以指定集合名称
		User user1 = new User();
		user1.setName("Jim");
		client.insert(user, "user");
		
		//1.3可插入内嵌集合
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setLoginId("admin");
		loginInfo.setPassword("12345");
		
		List<LoginLog> loginLogList = new ArrayList<LoginLog>();
		LoginLog loginLog1 = new LoginLog();
		loginLog1.setLogin_ip("127.0.0.1");
		loginLog1.setLogin_date(new Date());
		loginLogList.add(loginLog1);
		
		//user1.setLoginInfo(loginInfo);
		user1.setLoginLog(loginLogList);
		client.insert(user1, "user");
		
		//2.支持Map插入
		Map map = new HashMap();
		map.put("name", "Tom");
		map.put("age", 20);
		map.put("company", "dell");
		map.put("height", 178);
		map.put("weight", 69);
		
		client.insertMap(map, "user");
	}

	//@Test
	public void findByMap(){
		ApplicationContext context = new ClassPathXmlApplicationContext("mongo-config.xml");
		MongoDBClient client = context.getBean(MongoDBClient.class);
		
		//1.简单查询:where条件中都是key = value
		Map map = new HashMap();
		map.put("name", "Tom");
		
		//查询一条数据，若符合条件的数据有多条，则返回第一条
		User user = (User) client.findOne(map, User.class);
		if(user != null){
			logger.debug("user.name = " + user.getName() + " | user.age = " + user.getAge());
		}else{
			logger.debug("无记录");
		}
		
		//查询多条
		List<User> list = client.find(map, User.class);
		
		if(list != null && list.size() > 0){
			for(User u : list){
				logger.debug("user.name = " + u.getName() + " | user.age = " + u.getAge());
			}
		}else{
			logger.debug("无记录");
		}

	}
	
	//@Test
	public void findByQuery(){
		ApplicationContext context = new ClassPathXmlApplicationContext("mongo-config.xml");
		MongoDBClient client = context.getBean(MongoDBClient.class);
		
		MongoQuery mq = new MongoQuery();
		//MongoCriteria mc = new MongoCriteria();
		
		mq.add(MongoCriteria.is("age", 23)) ; //设置查询条件：年龄等于23的数据  ; where age = 23
		mq.add(MongoCriteria.greaterThan("height", 175)); //并且身高大于175 ; and height > 175
		
		List<User> list = (List<User>) client.find(mq , User.class) ;
		List<User> list1 = (List<User>) client.find(mq , User.class , "user") ;
		
		//若需要使用or做为条件关联，如：where age = 23 or height > 175
		mq = new MongoQuery();
		
		mq.add(MongoCriteria.or(MongoCriteria.is("age", 23) , MongoCriteria.greaterThan("height", 175)));
		
		List<User> list2 = (List<User>) client.find(mq , User.class) ;
		
		//可以设置排序,如身高大于等于180，按身高、年龄降序排序
		mq = new MongoQuery();
		mq.add(MongoCriteria.greaterThanEqual("height", 180));
		
		mq.setSort(MongoUtils.DESC, "height","age");
		List<User> list3 = (List<User>) client.find(mq , User.class) ;
		
		//若需要先按照身高降序排序，身高相同的话按照年龄升序排序
		mq = new MongoQuery();
		mq.add(MongoCriteria.greaterThanEqual("height", 180));
		
		mq.setSort(MongoUtils.DESC, "height");
		mq.setSort(MongoUtils.ASC, "age");
		
		List<User> list4 = (List<User>) client.find(mq , User.class) ;
		
		//可以设置分页查询
		mq = new MongoQuery();
		mq.add(MongoCriteria.greaterThanEqual("height", 160));
		
		mq.setPage(2, 5); //每页五条，查询第二页
		List<User> list5 = (List<User>) client.find(mq , User.class) ;
		
		//支持内嵌集合查询
		mq = new MongoQuery();
		//身高大于等于160，并且登录IP中包含字符串‘10’的
		//where height >= 160 and loginLog.login_ip like '%10%'
		mq.add(MongoCriteria.greaterThanEqual("height", 160));
		mq.add(MongoCriteria.like("loginLog.login_ip", "10")); 
	}
	
	//@Test
	public void update(){
		ApplicationContext context = new ClassPathXmlApplicationContext("mongo-config.xml");
		MongoDBClient client = context.getBean(MongoDBClient.class);
		
		//需要使用MongoUpdate
		MongoQuery mq = new MongoQuery();
		mq.add(MongoCriteria.is("name", "Tom"));
		
		MongoUpdate update = new MongoUpdate();
		update.set("company", "lenovo") ; //将姓名叫Tom的公司名称修改成lenovo
		
		int count = client.update(mq , update , User.class); //count表示更新了多少行数据
		
		//如果字段类型为数字（整型，浮点型），可以update增加/减少多少
		update.inc("height", 1) ; //将姓名叫Tom的身高增加一公分
		
		count = client.update(mq , update , "user");
	}
	
	//@Test
	public void delete(){
		ApplicationContext context = new ClassPathXmlApplicationContext("mongo-config.xml");
		MongoDBClient client = context.getBean(MongoDBClient.class);
		
		MongoQuery mq = new MongoQuery();
		mq.add(MongoCriteria.is("name", "Tom"));
		
		User user = (User) client.findOne(mq, User.class);
		
		//查询出来的结果直接进行删除
		client.delete(user) ;
		
		//通过设置查询条件，进行删除
		//首先支持将查询条件保存到Map中
		Map map = new HashMap();
		map.put("name", "Tom");
		client.delete(map, User.class) ; //将姓名为Tom的集合删除
		//client.delete(map, "user");
		
		//支持Query
		mq = new MongoQuery();
		mq.add(MongoCriteria.is("name", "Jim"));
		client.delete(mq, User.class);
		//client.delete(mq, "user");
		
	}
	
	//@Test
	public void testDate(){
		ApplicationContext context = new ClassPathXmlApplicationContext("mongo-config.xml");
		MongoDBClient client = context.getBean(MongoDBClient.class);
		
		MongoQuery mq = new MongoQuery();
		//MongoCriteria mc = new MongoCriteria();
		
		mq.add(MongoCriteria.is("name", "Jim")) ; //设置查询条件：年龄等于23的数据  ; where age = 23
		mq.add(MongoCriteria.is("loginInfo.password", "12345")); //并且身高大于175 ; and height > 175
		
		User user = (User) client.findOne(mq , User.class) ;
		
		if(user != null){
			logger.debug("user.name = " + user.getName() + " | user.age = " + user.getAge());
			
			List<LoginLog> loginLogList = user.getLoginLog() ;
			
			for(LoginLog loginLog : loginLogList){
				logger.debug("loginLog.getLogin_ip = " + loginLog.getLogin_ip() + " | loginLog.getLogin_date = " + loginLog.getLogin_date() + " | " + new Date());
			}
		}else{
			logger.debug("无记录");
		}
	}
	
	//@Test
	public void insertMany(){  
		for(int threadCount = 0 ; threadCount < 8 ; threadCount ++){
			Thread td = new InsertThread();
			td.setName("Insert Thread " + threadCount);
			td.start();
		}
	}
	
	public static void main(String[]  args){
		for(int threadCount = 0 ; threadCount < 10 ; threadCount ++){
			Thread td = new InsertThread();
			td.setName("Insert Thread " + threadCount);
			td.start();
		}
	}
	
	//@Test
	public void findSpeed(){
		ApplicationContext context = new ClassPathXmlApplicationContext("mongo-config.xml");
		MongoDBClient client = context.getBean(MongoDBClient.class);
		
		MongoQuery mq = new MongoQuery();
		//MongoCriteria mc = new MongoCriteria();
		
		mq.add(MongoCriteria.is("company", "Baidu")) ; //设置查询条件：年龄等于23的数据  ; where age = 23
		mq.add(MongoCriteria.greaterThanEqual("height", 180)) ; //并且身高大于175 ; and height > 175
		
		long startTime = System.currentTimeMillis();//获取当前时间
		List<User> user = (List<User>) client.find(mq , User.class) ;
		long endTime = System.currentTimeMillis();
		System.out.println("查询运行时间："+(endTime-startTime)+"ms");
	}
	
	public String getRandomString(int length){  
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  
        Random random = new Random();  
        StringBuffer sb = new StringBuffer();  
          
        for(int i = 0 ; i < length; ++i){  
            int number = random.nextInt(62);//[0,62)  
              
            sb.append(str.charAt(number));  
        }  
        return sb.toString();  
    }  
	
	public int getRandomInt(int min , int max){  
		Random random = new Random();
		return random.nextInt(max-min) + min;
	}
	
	public String getRandomCompany(){
		String[] companys = new String[]{"TKLife","Lenovo","IBM","HP","Oracle","Dell","Microsoft","Huawei","Baidu","Google","Sina","Sohu","Facebook"};
		
		int i = this.getRandomInt(0, 12) ;
		return companys[i];
	}
}
