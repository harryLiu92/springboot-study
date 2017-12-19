package com.liuhao.mybatis.sharding.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.liuhao.mybatis.sharding.holder.ShardingHolder;


@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class ResultSetInterceptor implements Interceptor {

	private final static String SELECT_FOUND_ROW = "SELECT FOUND_ROWS() as count";

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Connection connection = ShardingHolder.connectionHolder.get();
		
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		long totalCount = 0;
		try { 
        	countStmt = connection.prepareStatement(SELECT_FOUND_ROW);  
        	rs = countStmt.executeQuery();  
        	if (rs.next()) {  
        		totalCount = rs.getLong(1);  
        	} 

        	ShardingHolder.totalCountHolder.set(totalCount);

        } catch (SQLException e) {  
        	e.printStackTrace();  
        } finally {  
			try {
				if (rs != null) {
					rs.close();
				}
				if (countStmt != null) {
					countStmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();  
			}
        }  
		
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object arg0) {
		if (arg0 instanceof ResultSetHandler) {
			return Plugin.wrap(arg0, this);
		} else {
			return arg0;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

}
