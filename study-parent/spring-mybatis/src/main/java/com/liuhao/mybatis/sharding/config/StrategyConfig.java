package com.liuhao.mybatis.sharding.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

@XmlRootElement(name = "strategy")
public class StrategyConfig {

	private String table;

	private String column;

	private Object value;

	private String shardingTable;

	private String shardingDB;

	private TableStrategyConfig tableStrategyConfig;

	private DbStrategyConfig dbStrategyConfig;

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	@XmlElement(name = "tableStrategy")
	public TableStrategyConfig getTableStrategyConfig() {
		return tableStrategyConfig;
	}

	public void setTableStrategyConfig(TableStrategyConfig tableStrategyConfig) {
		this.tableStrategyConfig = tableStrategyConfig;
	}

	@XmlElement(name = "dbStrategy")
	public DbStrategyConfig getDbStrategyConfig() {
		return dbStrategyConfig;
	}

	public void setDbStrategyConfig(DbStrategyConfig dbStrategyConfig) {
		this.dbStrategyConfig = dbStrategyConfig;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getShardingTable() {
		return shardingTable;
	}

	public void setShardingTable(String shardingTable) {
		this.shardingTable = shardingTable;
	}

	public String getShardingDB() {
		return shardingDB;
	}

	public void setShardingDB(String shardingDB) {
		this.shardingDB = shardingDB;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
