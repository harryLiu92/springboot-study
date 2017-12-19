package com.liuhao.mybatis.sharding.parser.impl;

import com.liuhao.mybatis.sharding.parser.SqlParser;
import com.liuhao.mybatis.sharding.visitor.AbstractTablesNamesFinder;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;

public class SelectParser implements SqlParser{

	// 分页
	@Override
	public void doParser(Statement statement, AbstractTablesNamesFinder finder) {
		((Select) statement).accept(finder);
	}

}
