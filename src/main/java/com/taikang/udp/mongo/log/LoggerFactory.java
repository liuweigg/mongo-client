package com.taikang.udp.mongo.log;
import org.slf4j.Logger;
/**
 * copy from slf4j，简化程序中的logger创建
 * @author Johnny.Lu
 * 
 */
public class LoggerFactory {
	/**
	 * get a slf4j logger without passing a class
	 * @return
	 */
	public static Logger getLogger() {
        String name = new Exception().getStackTrace()[1].getClassName();
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(name);
		return logger;
    }
}
