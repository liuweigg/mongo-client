package com.taikang.udp.mongo;

import java.util.Date;

public class LoginLog {
	String login_ip;
	Date login_date;
	
	public String getLogin_ip() {
		return login_ip;
	}
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	public Date getLogin_date() {
		return login_date;
	}
	public void setLogin_date(Date login_date) {
		this.login_date = login_date;
	}
	
	
}
