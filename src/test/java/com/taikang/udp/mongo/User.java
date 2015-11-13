package com.taikang.udp.mongo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.taikang.udp.framework.common.datastructre.impl.BaseBO;

@Document
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String uid;
	private String name;
	private int age;
	private String gender ;
	private String company ;
	private int height ;
	private int weight ;
	private String aa;
	
	private LoginInfo loginInfo ;
	
	private List<LoginLog> loginLog ;
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public List<LoginLog> getLoginLog() {
		return loginLog;
	}
	public void setLoginLog(List<LoginLog> loginLog) {
		this.loginLog = loginLog;
	}
	
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	@Override
	public String toString() {
		return "{USER:{uid:"+this.uid+",name:"+this.name+",company:"+this.company+",height:"+this.height+",weight:"+this.weight+",age:"+this.age+"}}";
	}
	public String getAa() {
		return aa;
	}
	public void setAa(String aa) {
		this.aa = aa;
	}
	
}