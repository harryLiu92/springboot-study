package com.liuhao.mybatis.sharding.parser.impl;

import com.liuhao.mybatis.sharding.parser.SqlParser;
import com.liuhao.mybatis.sharding.visitor.AbstractTablesNamesFinder;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.update.Update;

public class UpdateParser implements SqlParser{

	@Override
	public void doParser(Statement statement, AbstractTablesNamesFinder finder) {
		((Update) statement).accept(finder);
	}

}
