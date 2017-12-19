package com.liuhao.mybatis.sharding.parser.impl;

import com.liuhao.mybatis.sharding.parser.SqlParser;
import com.liuhao.mybatis.sharding.visitor.AbstractTablesNamesFinder;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;

public class InsertParser implements SqlParser{

	@Override
	public void doParser(Statement statement, AbstractTablesNamesFinder finder) {
		((Insert) statement).accept(finder);
	}


}
