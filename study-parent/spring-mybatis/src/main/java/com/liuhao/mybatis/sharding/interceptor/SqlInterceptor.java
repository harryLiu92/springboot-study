package com.liuhao.mybatis.sharding.interceptor;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.google.common.collect.Lists;
import com.liuhao.mybatis.sharding.holder.ShardingHolder;
import com.liuhao.mybatis.sharding.interceptor.impl.PageSqlHandler;
import com.liuhao.mybatis.sharding.interceptor.impl.ShardingSqlHandler;

/**
 * @author liuhao 
 * @date   2017年11月17日下午2:26:45
 * SqlInterceptor没有做具体逻辑,实现在PageSqlHandler,ShardingSqlHandler
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class SqlInterceptor implements Interceptor {

	public static List<AbstractSqlHandler> providers = Lists.newArrayList(); 	    

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("===================SqlInterceptor====================");
		RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();

		Connection connection = (Connection) invocation.getArgs()[0];

		ShardingHolder.connectionHolder.set(connection);

		// register
		register(statementHandler);
		

		for (AbstractSqlHandler provider : providers) {

			provider.doInterceptor();

		}
		return invocation.proceed();
	}

	private void register(RoutingStatementHandler statementHandler) {
		new ShardingSqlHandler().register(statementHandler);
		new PageSqlHandler().register(statementHandler);
	}

	@Override
	public Object plugin(Object arg0) {
		if (arg0 instanceof StatementHandler) {
			return Plugin.wrap(arg0, this);
		} else {
			return arg0;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		
	}
}
