package com.liuhao.mybatis.sharding.strategy.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.liuhao.common.utils.CommonUtils;
import com.liuhao.mybatis.sharding.strategy.ShardingStrategy;

public class RangeShardingStrategy extends ShardingStrategy {

	public int getStrategy(Object state, Object value) {

		long convertState = CommonUtils.parserLong(state);
		long convertValue = CommonUtils.parserLong(value);

		if (convertState == 0) {
			throw new IllegalStateException(" state can't be zero ");
		}

		BigDecimal index = new BigDecimal(convertValue).divide(new BigDecimal(convertState), 0, RoundingMode.UP);

		return index.intValue();
	}
}
