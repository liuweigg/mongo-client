package com.taikang.udp.mongo.vo;

public class AggregationVo {
	private String column ;
	
	private String function ;
	
	private String as ;

	public AggregationVo(){ 
		
	}
	
	public AggregationVo(String column , String function ,String as ){
		this.column = column ;
		this.function = function ;
		this.as = as ;
	}
	
	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getAs() {
		return as;
	}

	public void setAs(String as) {
		this.as = as;
	}
	
	
}
