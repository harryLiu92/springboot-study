package com.liuhao.mybatis.sharding.strategy;

import com.liuhao.mybatis.sharding.DynamicDataSource;
import com.liuhao.mybatis.sharding.config.StrategyConfig;

/**
 * @since  1.0.0
 * @author liuhao 
 * @date   2017年11月7日上午11:23:48
 */
public abstract class ShardingStrategy {

	public static final String UNDERLINE = "_";

	public abstract int getStrategy(Object state, Object value);

	public void setSharding(StrategyConfig config) {
		setShardingTable(config);

		setShardingDB(config);
	}

	private void setShardingTable(StrategyConfig config) {

		StringBuilder newTableName = new StringBuilder(config.getTable());
		
		if (config.getTableStrategyConfig() != null) {

			int index = getStrategy(config.getTableStrategyConfig().getState(), config.getValue());
	
			newTableName.append(UNDERLINE).append(String.valueOf(index));
		}

		config.setShardingTable(newTableName.toString());
	}

	private void setShardingDB(StrategyConfig config) {
		String shardingDB = DynamicDataSource.DEFUALT;

		if (config.getDbStrategyConfig() != null) {
			
			shardingDB = String.valueOf(getStrategy(config.getDbStrategyConfig().getState(), config.getValue()));
			
		}

		config.setShardingDB(shardingDB);
	}

}
