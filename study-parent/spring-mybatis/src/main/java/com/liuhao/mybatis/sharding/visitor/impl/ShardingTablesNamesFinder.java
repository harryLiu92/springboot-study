package com.liuhao.mybatis.sharding.visitor.impl;

import com.liuhao.mybatis.sharding.config.StrategyConfig;
import com.liuhao.mybatis.sharding.provider.StrategyProvider;
import com.liuhao.mybatis.sharding.visitor.AbstractTablesNamesFinder;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.update.Update;

public class ShardingTablesNamesFinder extends AbstractTablesNamesFinder {

	public void visit(Table table) {
		StrategyConfig strategyConfig = StrategyProvider.start(table);

		if (strategyConfig != null)
			table.setName(strategyConfig.getShardingTable());

		super.visit(table);
	}

	public void visit(Delete delete) {
		
		if (delete.getTable() != null)
			visit(delete.getTable());

		super.visit(delete);
	}

	public void visit(Update update) {
		for (Table table : update.getTables()) {
			visit(table);
		}

		super.visit(update);
	}

	public void visit(Insert insert) {
		if (insert.getTable() != null)
			visit(insert.getTable());

		super.visit(insert);
	}

}