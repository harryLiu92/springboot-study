package com.liuhao.mybatis.sharding.interceptor.impl;

import org.apache.ibatis.mapping.BoundSql;

import com.liuhao.common.utils.ReflectUtil;
import com.liuhao.mybatis.sharding.holder.ShardingHolder;
import com.liuhao.mybatis.sharding.interceptor.AbstractSqlHandler;
import com.liuhao.mybatis.sharding.provider.ShardingProvider;
import com.liuhao.mybatis.sharding.visitor.impl.ShardingTablesNamesFinder;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;

public class ShardingSqlHandler extends AbstractSqlHandler {

	@Override
	public void doInterceptor() {
		try {

			ShardingProvider.start();


			BoundSql boundSql = (BoundSql) getValue("boundSql");
			ShardingHolder.boundSqlHolder.set(boundSql);

			String sql = (String) getValue("sql");

			// jsqlparser
			Statement statement = CCJSqlParserUtil.parse(sql);

			ShardingTablesNamesFinder finder = new ShardingTablesNamesFinder();

			parserMap.get(statement.getClass()).doParser(statement, finder);

			ReflectUtil.setFieldValue(boundSql, "sql", statement.toString());

		} catch (JSQLParserException e) {
			e.printStackTrace();
		}

	}

}
