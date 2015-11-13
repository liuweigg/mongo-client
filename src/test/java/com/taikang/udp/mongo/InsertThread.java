package com.taikang.udp.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taikang.udp.mongo.context.MongoDBClient;

public class InsertThread extends Thread {

	@Override
	public void run() {


		ApplicationContext context = new ClassPathXmlApplicationContext("mongo-config.xml");
		MongoDBClient client = context.getBean(MongoDBClient.class);
		
		List<User> list = new ArrayList<User>(5000);
		TestMongoClient testMongoClient = new TestMongoClient();
		
		int count = 0 ;
		
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 1000000 ; i ++){
			//1.支持对象的插入
			User user = new User();
			user.setName(testMongoClient.getRandomString(6));
			user.setAge(testMongoClient.getRandomInt(18, 60));
			user.setCompany(testMongoClient.getRandomCompany());
			user.setHeight(testMongoClient.getRandomInt(140, 220));
			user.setWeight(testMongoClient.getRandomInt(40, 150));
			
			//1.3可插入内嵌集合
			/*LoginInfo loginInfo = new LoginInfo();
			loginInfo.setLoginId(testMongoClient.getRandomString(8));
			loginInfo.setPassword(testMongoClient.getRandomString(8));

			user.setLoginInfo(loginInfo);
			
			List<LoginLog> loginLogList = new ArrayList<LoginLog>();
			LoginLog loginLog1 = new LoginLog();
			loginLog1.setLogin_ip("127.0.0.1");
			loginLog1.setLogin_date(new Date());
			loginLogList.add(loginLog1);
			
			user.setLoginLog(loginLogList);*/
			
			list.add(user);
			
			if(0 == list.size() % 5000){
				System.out.println(this.getName() + " | count = "+ (++count * 5000));
				client.insertList(list);
				list.clear();
			}
			
		}
	
	}
	
}
