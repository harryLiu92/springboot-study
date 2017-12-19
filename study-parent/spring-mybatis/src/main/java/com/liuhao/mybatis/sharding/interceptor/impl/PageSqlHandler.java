package com.liuhao.mybatis.sharding.interceptor.impl;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.RowBounds;

import com.liuhao.common.utils.ReflectUtil;
import com.liuhao.mybatis.sharding.interceptor.AbstractSqlHandler;

public class PageSqlHandler extends AbstractSqlHandler {

	private final static String LIMIT = " LIMIT %s, %s ";

	private final static String SELECT = "SELECT";

	private final static String SELECT_SQL_CALC_FOUND_ROW = " SELECT SQL_CALC_FOUND_ROWS ";

	@Override
	public void doInterceptor() {

		BoundSql boundSql = (BoundSql) getValue("boundSql");

		String sql = (String) getValue("sql");

		RowBounds rowBounds = (RowBounds) getValue("rowBounds");

		if (isNotDefault(rowBounds)) {

			// replace select
			sql = sql.trim();
			sql = SELECT_SQL_CALC_FOUND_ROW + sql.substring(SELECT.length(), sql.length());

			// append limit
			StringBuilder limitFormat = new StringBuilder(LIMIT);
			String limit = String.format(limitFormat.toString(),
					new Object[] { rowBounds.getOffset(), rowBounds.getLimit() });

			sql = sql + limit;

			ReflectUtil.setFieldValue(boundSql, "sql", sql);
		}
	}

	private boolean isNotDefault(RowBounds rowBounds) {
		if (rowBounds == null) {
			return false;
		}
		return RowBounds.NO_ROW_LIMIT != rowBounds.getLimit() || RowBounds.NO_ROW_OFFSET != rowBounds.getOffset();
	}
}
