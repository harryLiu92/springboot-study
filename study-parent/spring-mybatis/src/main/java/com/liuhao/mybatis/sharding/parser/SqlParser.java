package com.liuhao.mybatis.sharding.parser;

import com.liuhao.mybatis.sharding.visitor.AbstractTablesNamesFinder;

import net.sf.jsqlparser.statement.Statement;

public interface SqlParser {

	void doParser(Statement statement, AbstractTablesNamesFinder finder);
}
