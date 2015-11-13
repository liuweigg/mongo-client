package com.taikang.udp.mongo.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import com.taikang.udp.mongo.util.MongoUtils;
import com.taikang.udp.mongo.vo.AggregationVo;

/**
 * 对Aggregation的封装
 * 主要实现group by 的功能
 * 以及聚合函数：count/sum/min/max/first/last/avg
 * 
 * @author liuwei117
 * @version [版本号，默认V1.0.0]
 * @Credited 2015年10月29日 上午9:53:29
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class MongoAggregation {
	
	private String[]  groupFields;			//group by 后的字段，即按照哪些字段进行分组
	private List<AggregationVo> aggList ;	//select ... from 中间的字段，都为聚合函数，分组字段不需要额外声明；聚合函数的结果需要指定别名，并和查询结果接收对象的字段一致
	private List<Criteria> criterias;		//查询条件
	private List<Sort> sorts ;				//排序
	private List<String> asFieldList;		//聚合函数别名集合
	private String returnField ;			//分组字段返回
	
	public MongoAggregation(){
		//group = new ArrayList<String>();
		this.aggList   = new ArrayList<AggregationVo>();
		this.criterias = new ArrayList<Criteria>();
		this.sorts	  = new ArrayList<Sort>();
		this.asFieldList = new ArrayList<String>();
	}

	public MongoAggregation(String... groupFields){
		//group = new ArrayList<String>();
		this.aggList   = new ArrayList<AggregationVo>();
		this.criterias = new ArrayList<Criteria>();
		this.sorts	  = new ArrayList<Sort>();
		this.asFieldList = new ArrayList<String>();
		this.setGroup(groupFields);
	}
	/**
	 * 设置group by字段
	 * @param groupFields
	 */
	public void setGroup(String... groupFields){
		this.groupFields = groupFields ;
		
		if("".equals(returnField)){
			returnField = groupFields[0] ;
		}
		//group.add(groupString);
	}
	
	/**
	 * count
	 * @param as 别名
	 */
	public void count(String as){
		aggList.add(new AggregationVo(null , MongoUtils.COUNT , as));
		asFieldList.add(as);
	}
	
	/**
	 * sum
	 * @param column sum(column)
	 * @param as 别名
	 */
	public void sum(String column , String as){
		aggList.add(new AggregationVo(column , MongoUtils.SUM , as));
		asFieldList.add(as);
	}
	
	/**
	 * max
	 * @param column max(column)
	 * @param as 别名
	 */
	public void max(String column , String as){
		aggList.add(new AggregationVo(column , MongoUtils.MAX , as));
		asFieldList.add(as);
	}
	
	/**
	 * min
	 * @param column min(column)
	 * @param as 别名
	 */
	public void min(String column , String as){
		aggList.add(new AggregationVo(column , MongoUtils.MIN , as));
		asFieldList.add(as);
	}
	
	/**
	 * first 所有数据取第一个
	 * @param column first(column)
	 * @param as 别名
	 */
	public void first(String column , String as){
		aggList.add(new AggregationVo(column , MongoUtils.FIRST , as));
		asFieldList.add(as);
	}
	
	/**
	 * last 所有数据取最后一个
	 * @param column last(column)
	 * @param as 别名
	 */
	public void last(String column , String as){
		aggList.add(new AggregationVo(column , MongoUtils.LAST , as));
		asFieldList.add(as);
	}
	
	/**
	 * avg
	 * @param column avg(column)
	 * @param as 别名
	 */
	public void avg(String column , String as){
		aggList.add(new AggregationVo(column , MongoUtils.AVG , as));
		asFieldList.add(as);
	}
	
	/**
	 * Adds the given {@link CriteriaDefinition} to the current {@link Query}.
	 * 
	 * @param criteriaDefinition must not be {@literal null}.
	 * @return
	 * @since 1.6
	 */
	public void addMatchCriteria(Criteria criteria) {
		criterias.add(criteria);
	}
	
	/**
	 * Adds the given {@link CriteriaDefinition} to the current {@link Query}.
	 * 
	 * @param criteriaDefinition must not be {@literal null}.
	 * @return
	 * @since 1.6
	 */
	public void addMatch(Criteria criteria) {
		criterias.add(criteria);
	}
	
	/**
	 * 设置排序条件
	 * @param direction
	 * @param properties
	 */
	public void addSort(String direction, String... properties){
		sorts.add(new Sort(MongoUtils.getOrder(direction) , properties));
	}

	/**
	 * 当分组字段为多个的时候，设置返回值存放的字段
	 * 如：group by gender,company
	 *    设置 returnField = Column
	 * 	  Column = { "gender" : "M" , "company" : "CompanyName"}
	 * @param returnField
	 */
	public void setReturnField(String returnField) {
		this.returnField = returnField;
	}

	public Aggregation formatAggregation(){
		GroupOperation group = group(groupFields);
		
		for(AggregationVo aggre : aggList){
			if(aggre != null ){
				if(MongoUtils.COUNT.equals(aggre.getFunction())){
					group = group.count().as(aggre.getAs());
				}else if(MongoUtils.SUM.equals(aggre.getFunction())){
					group = group.sum(aggre.getColumn()).as(aggre.getAs());
				}else if(MongoUtils.MAX.equals(aggre.getFunction())){
					group = group.max(aggre.getColumn()).as(aggre.getAs());
				}else if(MongoUtils.MIN.equals(aggre.getFunction())){
					group = group.min(aggre.getColumn()).as(aggre.getAs());
				}else if(MongoUtils.FIRST.equals(aggre.getFunction())){
					group = group.first(aggre.getColumn()).as(aggre.getAs());
				}else if(MongoUtils.LAST.equals(aggre.getFunction())){
					group = group.last(aggre.getColumn()).as(aggre.getAs());
				}else if(MongoUtils.AVG.equals(aggre.getFunction())){
					group = group.avg(aggre.getColumn()).as(aggre.getAs());
				}
				
			}
		}

		Aggregation aggregation = newAggregation(group,
									project(MongoUtils.listToArray(asFieldList))
										.and(returnField).previousOperation());
		
		//查询条件
		for(Criteria criteria : criterias){
			if(criteria != null){
				aggregation.match(criteria) ;
			}
		}
		
		//返回值
		/*aggregation.project("userCount","ageSum","maxAge","minAge","firstName","lastName","avgAge")
					.and("column").previousOperation();*/
		
		//排序
		for(Sort sort : sorts){
			aggregation.sort(sort);
		}
		
		return aggregation ;
	}
}
