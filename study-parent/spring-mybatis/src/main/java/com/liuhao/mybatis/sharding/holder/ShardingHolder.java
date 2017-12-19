package com.liuhao.mybatis.sharding.holder;

import org.apache.ibatis.mapping.BoundSql;

import java.sql.Connection;

public class ShardingHolder {

	public static final ThreadLocal<BoundSql> boundSqlHolder = new ThreadLocal<BoundSql>();

	public static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
	
	public static final ThreadLocal<Long> totalCountHolder = new ThreadLocal<Long>();

}
