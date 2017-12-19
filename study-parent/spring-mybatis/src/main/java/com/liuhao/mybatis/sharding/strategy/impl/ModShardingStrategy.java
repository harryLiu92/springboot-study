package com.liuhao.mybatis.sharding.strategy.impl;

import com.liuhao.common.utils.CommonUtils;
import com.liuhao.mybatis.sharding.strategy.ShardingStrategy;

public class ModShardingStrategy extends ShardingStrategy {

	public int getStrategy(Object state, Object value) {

		long convertState = CommonUtils.parserLong(state);
		long convertValue = CommonUtils.parserLong(value);
		
		if (convertState == 0) {
			throw new IllegalStateException(" state can't be zero ");
		}

		// mod
		long index = convertValue % convertState;

		return (int)index;
	}

}
