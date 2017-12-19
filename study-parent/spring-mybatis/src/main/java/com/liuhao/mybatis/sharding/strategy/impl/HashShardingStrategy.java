package com.liuhao.mybatis.sharding.strategy.impl;

import com.liuhao.common.utils.CommonUtils;
import com.liuhao.mybatis.sharding.strategy.ShardingStrategy;

public class HashShardingStrategy extends ShardingStrategy {

	public int getStrategy(Object state, Object value) {

		long convertState = CommonUtils.parserLong(state);
		
		if (convertState == 0) {
			throw new IllegalStateException(" state can't be zero ");
		}
		
		// 哈希后
		int hashCode = Math.abs(value.hashCode());

		int index = (int) (hashCode % convertState);
		
		return index;
	}

}
