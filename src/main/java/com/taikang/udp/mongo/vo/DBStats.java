package com.taikang.udp.mongo.vo;

import com.mongodb.CommandResult;

public class DBStats {
	
	String serverUsed;	//IP+port
	String db;			//数据库名称
	Double collections;	//集合数量
	Double objects;		//对象数量
	Double dataSize;	//数据大小
	Double avgObjSize;	//对象平均大小
	Double storageSize;	//数据大小(含预分配空间)
	Double numExtents;	//事件数量
	Double indexes;		//索引数量
	Double indexSize;	//索引大小
	Double fileSize;	//文件大小
	Double nsSizeMB;
	
	public String getServerUsed() {
		return serverUsed;
	}
	public void setServerUsed(String serverUsed) {
		this.serverUsed = serverUsed;
	}
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public Double getAvgObjSize() {
		return avgObjSize;
	}
	public void setAvgObjSize(Double avgObjSize) {
		this.avgObjSize = avgObjSize;
	}
	public Double getCollections() {
		return collections;
	}
	public void setCollections(Double collections) {
		this.collections = collections;
	}
	public Double getObjects() {
		return objects;
	}
	public void setObjects(Double objects) {
		this.objects = objects;
	}
	public Double getDataSize() {
		return dataSize;
	}
	public void setDataSize(Double dataSize) {
		this.dataSize = dataSize;
	}
	public Double getStorageSize() {
		return storageSize;
	}
	public void setStorageSize(Double storageSize) {
		this.storageSize = storageSize;
	}
	public Double getNumExtents() {
		return numExtents;
	}
	public void setNumExtents(Double numExtents) {
		this.numExtents = numExtents;
	}
	public Double getIndexes() {
		return indexes;
	}
	public void setIndexes(Double indexes) {
		this.indexes = indexes;
	}
	public Double getIndexSize() {
		return indexSize;
	}
	public void setIndexSize(Double indexSize) {
		this.indexSize = indexSize;
	}
	public Double getFileSize() {
		return fileSize;
	}
	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}
	public Double getNsSizeMB() {
		return nsSizeMB;
	}
	public void setNsSizeMB(Double nsSizeMB) {
		this.nsSizeMB = nsSizeMB;
	}
	
	public void populateCommandResult(CommandResult commandResult){
		this.serverUsed = commandResult.getString("serverUsed");	//IP+port
		this.db = commandResult.getString("db");					//数据库名称
		this.collections = commandResult.getDouble("collections");	//集合数量
		this.objects = commandResult.getDouble("objects");			//对象数量
		this.dataSize = commandResult.getDouble("dataSize");		//数据大小
		this.avgObjSize = commandResult.getDouble("avgObjSize");	//对象平均大小
		this.storageSize = commandResult.getDouble("storageSize");	//数据大小(含预分配空间)
		this.numExtents = commandResult.getDouble("numExtents");	//事件数量
		this.indexes = commandResult.getDouble("indexes");			//索引数量
		this.indexSize = commandResult.getDouble("indexSize");		//索引大小
		this.fileSize = commandResult.getDouble("fileSize");		//文件大小
		this.nsSizeMB = commandResult.getDouble("nsSizeMB");
	}
}
