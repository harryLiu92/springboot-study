package com.liuhao.mybatis.sharding.parser.impl;

import com.liuhao.mybatis.sharding.parser.SqlParser;
import com.liuhao.mybatis.sharding.visitor.AbstractTablesNamesFinder;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;

public class DeleteParser implements SqlParser{

	@Override
	public void doParser(Statement statement, AbstractTablesNamesFinder finder) {
		((Delete) statement).accept(finder);
	}

}
