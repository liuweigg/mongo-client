package com.taikang.udp.mongo.vo;

import com.mongodb.CommandResult;

public class CollectionStats {
	
	String serverUsed ; 		//IP+port
	String collectionName ;		//数据库名称.集合名称
	Double count ;				//文档数量
	Double size ;				//集合大小
	Double avgObjSize;			//每条文档的平均值
	Double numExtents ;			//事件数
	Double storageSize ;		//预分配的存储空间
	Double totalIndexSize ;		//索引总大小
	Double nindexes ;			//索引数量
	
	public String getServerUsed() {
		return serverUsed;
	}
	public void setServerUsed(String serverUsed) {
		this.serverUsed = serverUsed;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	public Double getCount() {
		return count;
	}
	public void setCount(Double count) {
		this.count = count;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public Double getAvgObjSize() {
		return avgObjSize;
	}
	public void setAvgObjSize(Double avgObjSize) {
		this.avgObjSize = avgObjSize;
	}
	public Double getNumExtents() {
		return numExtents;
	}
	public void setNumExtents(Double numExtents) {
		this.numExtents = numExtents;
	}
	public Double getStorageSize() {
		return storageSize;
	}
	public void setStorageSize(Double storageSize) {
		this.storageSize = storageSize;
	}
	public Double getTotalIndexSize() {
		return totalIndexSize;
	}
	public void setTotalIndexSize(Double totalIndexSize) {
		this.totalIndexSize = totalIndexSize;
	}
	public Double getNindexes() {
		return nindexes;
	}
	public void setNindexes(Double nindexes) {
		this.nindexes = nindexes;
	}
	
	public void populateCommandResult(CommandResult commandResult){
		this.serverUsed = commandResult.getString("serverUsed");	//IP+port
		this.collectionName = commandResult.getString("ns");					//数据库名称
		
		this.count = commandResult.getDouble("count");
		this.size = commandResult.getDouble("size");
		this.avgObjSize = commandResult.getDouble("avgObjSize");
		this.numExtents = commandResult.getDouble("numExtents");
		this.storageSize = commandResult.getDouble("storageSize");
		this.totalIndexSize = commandResult.getDouble("totalIndexSize");
		this.nindexes = commandResult.getDouble("nindexes");
	}
}
