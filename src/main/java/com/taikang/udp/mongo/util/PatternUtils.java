package com.taikang.udp.mongo.util;

import java.util.regex.Pattern;

/**
 * 常用的正则表达式
 * 
 * @author liuwei117
 * @version V1.0.0
 * @Credited 2015年10月19日 下午4:46:15
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class PatternUtils { 
	
	/**
	 * like '%key%'
	 * @param value
	 * @return
	 */
	public static Pattern Like(String value){
		// as SQL:  like " '%" + key + "%' "  
		return Pattern.compile("^.*" + value + ".*$", 
				 Pattern.CASE_INSENSITIVE);
	}

	/**
	 * like 'key%'
	 * @param value
	 * @return
	 */
	public static Pattern StartWith(String value){
		// as SQL:  like " '" + key + "%' "  
		return Pattern.compile( "^" + value, 
				 Pattern.CASE_INSENSITIVE);
	}
	

	/**
	 * like '%key'
	 * @param value
	 * @return
	 */
	public static Pattern EndWith(String value){
		// as SQL:  like " '%" + key + "' "  
		return Pattern.compile( value + "$" , 
				 Pattern.CASE_INSENSITIVE);
	}
}
