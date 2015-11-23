package com.taikang.udp.mongo.vo;

import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;

public class ServerStats {
	
	/**
	 * 连接
	 * db.serverStatus().connections
	 */
	Double currentConnections;			//当前的连接
	Double availableConnections;		//可用连接
	Double totalCreatedConnections;		//所有创建过的连接

	/**
	 * 内存使用
	 * db.serverStatus().mem
	 */
	Double residentMemory;				//在物理内存中的数据（MB）
	Double virtualMemory;				//虚拟内存(页面文件)使用（MB）
	Double mappedMemory;				//映射文件大小
	Double mappedWithJournalMemory;		//为journal提供内存,一般是mapped的2倍

	/**
	 * 操作计数器
	 * db.serverStatus().opcounters
	 */
	Double insertOperations;			//server启动以来总的insert数据量		
	Double queryOperations;				//server启动以来总的query数据量
	Double updateOperations;			//server启动以来总的update数据量
	Double deleteOperations;			//server启动以来总的delete数据量
	Double getmoreOperations;			//server启动以来调用任何游标的getMore总次数
	Double commandOperations;			//server启动以来执行其他命令的总次数

	/**
	 * 复制集操作集数
	 * db.serverStatus().opcountersRepl
	 */
	Double insertReplications;			//server启动以来总复本集insert数据量
	Double queryReplications;			//server启动以来总复本集query数据量
	Double updateReplications;			//server启动以来总复本集update数据量
	Double deleteReplications;			//server启动以来总复本集delete数据量
	Double getmoreReplications;			//server启动以来总复本集调用任何游标的getMore总次数
	Double commandReplications;			//server启动以来总复本集执行其他命令的总次数
	
	/**
	 * 网络
	 * db.serverStatus().network
	 */
	Double bytesIn;						//发送到数据库的数据总量（bytes）
	Double bytesOut;					//数据库发出的数据总量（bytes）
	Double numRequests;					//发送到数据库的请求量
	
	public Double getCurrentConnections() {
		return currentConnections;
	}
	public void setCurrentConnections(Double currentConnections) {
		this.currentConnections = currentConnections;
	}
	public Double getAvailableConnections() {
		return availableConnections;
	}
	public void setAvailableConnections(Double availableConnections) {
		this.availableConnections = availableConnections;
	}
	public Double getTotalCreatedConnections() {
		return totalCreatedConnections;
	}
	public void setTotalCreatedConnections(Double totalCreatedConnections) {
		this.totalCreatedConnections = totalCreatedConnections;
	}
	public Double getResidentMemory() {
		return residentMemory;
	}
	public void setResidentMemory(Double residentMemory) {
		this.residentMemory = residentMemory;
	}
	public Double getVirtualMemory() {
		return virtualMemory;
	}
	public void setVirtualMemory(Double virtualMemory) {
		this.virtualMemory = virtualMemory;
	}
	public Double getMappedMemory() {
		return mappedMemory;
	}
	public void setMappedMemory(Double mappedMemory) {
		this.mappedMemory = mappedMemory;
	}
	public Double getMappedWithJournalMemory() {
		return mappedWithJournalMemory;
	}
	public void setMappedWithJournalMemory(Double mappedWithJournalMemory) {
		this.mappedWithJournalMemory = mappedWithJournalMemory;
	}
	public Double getInsertOperations() {
		return insertOperations;
	}
	public void setInsertOperations(Double insertOperations) {
		this.insertOperations = insertOperations;
	}
	public Double getQueryOperations() {
		return queryOperations;
	}
	public void setQueryOperations(Double queryOperations) {
		this.queryOperations = queryOperations;
	}
	public Double getUpdateOperations() {
		return updateOperations;
	}
	public void setUpdateOperations(Double updateOperations) {
		this.updateOperations = updateOperations;
	}
	public Double getDeleteOperations() {
		return deleteOperations;
	}
	public void setDeleteOperations(Double deleteOperations) {
		this.deleteOperations = deleteOperations;
	}
	public Double getGetmoreOperations() {
		return getmoreOperations;
	}
	public void setGetmoreOperations(Double getmoreOperations) {
		this.getmoreOperations = getmoreOperations;
	}
	public Double getCommandOperations() {
		return commandOperations;
	}
	public void setCommandOperations(Double commandOperations) {
		this.commandOperations = commandOperations;
	}
	public Double getInsertReplications() {
		return insertReplications;
	}
	public void setInsertReplications(Double insertReplications) {
		this.insertReplications = insertReplications;
	}
	public Double getQueryReplications() {
		return queryReplications;
	}
	public void setQueryReplications(Double queryReplications) {
		this.queryReplications = queryReplications;
	}
	public Double getUpdateReplications() {
		return updateReplications;
	}
	public void setUpdateReplications(Double updateReplications) {
		this.updateReplications = updateReplications;
	}
	public Double getDeleteReplications() {
		return deleteReplications;
	}
	public void setDeleteReplications(Double deleteReplications) {
		this.deleteReplications = deleteReplications;
	}
	public Double getGetmoreReplications() {
		return getmoreReplications;
	}
	public void setGetmoreReplications(Double getmoreReplications) {
		this.getmoreReplications = getmoreReplications;
	}
	public Double getCommandReplications() {
		return commandReplications;
	}
	public void setCommandReplications(Double commandReplications) {
		this.commandReplications = commandReplications;
	}
	public Double getBytesIn() {
		return bytesIn;
	}
	public void setBytesIn(Double bytesIn) {
		this.bytesIn = bytesIn;
	}
	public Double getBytesOut() {
		return bytesOut;
	}
	public void setBytesOut(Double bytesOut) {
		this.bytesOut = bytesOut;
	}
	public Double getNumRequests() {
		return numRequests;
	}
	public void setNumRequests(Double numRequests) {
		this.numRequests = numRequests;
	}
	
	public void populateCommandResult(CommandResult commandResult){
		
		BasicDBObject connections = (BasicDBObject)commandResult.get("connections");
		this.currentConnections = connections.getDouble("current");
		this.availableConnections = connections.getDouble("available");
		this.totalCreatedConnections = connections.getDouble("totalCreated");
		
		BasicDBObject mem = (BasicDBObject)commandResult.get("mem");
		this.residentMemory = mem.getDouble("resident");
		this.virtualMemory = mem.getDouble("virtual");
		this.mappedMemory = mem.getDouble("mapped");
		this.mappedWithJournalMemory = mem.getDouble("mappedWithJournal");
		
		BasicDBObject opcounters = (BasicDBObject)commandResult.get("opcounters");
		this.insertOperations = opcounters.getDouble("insert");
		this.queryOperations = opcounters.getDouble("query");
		this.updateOperations = opcounters.getDouble("update");
		this.deleteOperations = opcounters.getDouble("delete");
		this.getmoreOperations = opcounters.getDouble("getmore");
		this.commandOperations = opcounters.getDouble("command");

		BasicDBObject opcountersRepl = (BasicDBObject)commandResult.get("opcountersRepl");
		this.insertOperations = opcountersRepl.getDouble("insert");
		this.queryOperations = opcountersRepl.getDouble("query");
		this.updateOperations = opcountersRepl.getDouble("update");
		this.deleteOperations = opcountersRepl.getDouble("delete");
		this.getmoreOperations = opcountersRepl.getDouble("getmore");
		this.commandOperations = opcountersRepl.getDouble("command");

		BasicDBObject network = (BasicDBObject)commandResult.get("network");
		this.bytesIn = network.getDouble("bytesIn");
		this.bytesOut = network.getDouble("bytesOut");
		this.numRequests = network.getDouble("numRequests");
		
	}	
}
