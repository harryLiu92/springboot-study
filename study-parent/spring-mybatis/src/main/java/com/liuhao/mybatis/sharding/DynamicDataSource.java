package com.liuhao.mybatis.sharding;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.liuhao.mybatis.sharding.config.StrategyConfig;
import com.liuhao.mybatis.sharding.provider.StrategyProvider;



public class DynamicDataSource extends AbstractRoutingDataSource {

	public static final String DEFUALT = "0";

	@Override
	protected Object determineCurrentLookupKey() {
		System.out.println("-------------------DynamicDataSource-------------------");
		String dbKey = DEFUALT;

		StrategyConfig strategyConfig = StrategyProvider.get();

		if (strategyConfig != null)
			dbKey = strategyConfig.getShardingDB();

		return dbKey;
	}

}