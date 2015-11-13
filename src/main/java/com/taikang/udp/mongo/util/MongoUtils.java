package com.taikang.udp.mongo.util;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.expression.TypedValue;

/**
 * 静态变量、工具方法
 * 
 * @author liuwei117
 * @version V1.0.0
 * @Credited 2015年10月19日 下午4:45:52
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class MongoUtils {
	//null
	public static final TypedValue NULL = TypedValue.NULL;
	
	//true
	public static final boolean TRUE = true ;
	
	//false
	public static final boolean FALSE = false ;
	
	//升序
	public static final String ASC = "1" ;
	
	//降序
	public static final String DESC = "-1" ;
	
	public static Direction getOrder(String direction){
		
		if(MongoUtils.ASC.equals(direction)){
			//升序
			return Direction.ASC ;
		}else if(MongoUtils.DESC.equals(direction)){
			//降序
			return Direction.DESC ;
		}
		
		return Direction.ASC;
	}
	
	public static final String COUNT 	= 	"count";
	
	public static final String SUM 		= 	"sum";
	
	public static final String MAX 		= 	"max";
	
	public static final String MIN 		= 	"min";
	
	public static final String FIRST 	= 	"first";
	
	public static final String LAST 	= 	"last";
	
	public static final String AVG 		= 	"avg";
	
	public static final String STRING_SPLIT 		= 	",";
	
	/**
	 * 合并两个数组
	 * @param array
	 * @param arrayOther
	 * @return
	 */
	public static String[] mergeArray(Object[] array , Object[] arrayOther){
		String[] returnArray = new String[array.length + arrayOther.length];
		
		System.arraycopy(array, 0, returnArray, 0, array.length);
		System.arraycopy(arrayOther, 0, returnArray, array.length, arrayOther.length);
		
		return returnArray ;
	}
	
	/**
	 * 将list转成数组
	 * @param list
	 * @return
	 */
	public static String[] listToArray(List<String> list){
		String[] returnArray = new String[list.size()];
		
		for(int i = 0 ; i < list.size() ; i ++){
			returnArray[i] = list.get(i);
		}
		
		return returnArray ;
	}
}
