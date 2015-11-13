package com.taikang.udp.mongo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.taikang.udp.framework.common.datastructre.impl.BaseBO;

@Document
public class LoginInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String loginId;
	private String password;
	public String getLoginId() {
		return loginId;
	} 
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}