package com.liuhao.mybatis.sharding.provider;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.liuhao.common.utils.ReflectUtil;
import com.liuhao.mybatis.sharding.config.ShardingConfig;
import com.liuhao.mybatis.sharding.config.StrategyConfig;
import com.liuhao.mybatis.sharding.holder.ShardingHolder;
import com.liuhao.mybatis.sharding.strategy.ShardingStrategy;

import net.sf.jsqlparser.schema.Table;

public class StrategyProvider {

	private static final ThreadLocal<StrategyConfig> strategyConfigHolder = new ThreadLocal<StrategyConfig>();
	
	public static StrategyConfig start(Table table) {
		ShardingConfig shardingConfig = ShardingProvider.get();

		if (shardingConfig == null) {
			return get();
		}

		for (StrategyConfig config : shardingConfig.getStrategyConfigs()) {
			String tableName = convert(table.getName());
			if (StringUtils.equals(config.getTable(), tableName)) {

				config.setValue(getParamValue(ShardingHolder.boundSqlHolder.get().getParameterObject(), config));

				ShardingStrategy strategy = null;
				try {
					strategy = (ShardingStrategy)config.getTableStrategyConfig().getClazz().newInstance();
				} catch (Exception e) {
					throw new RuntimeException(" ClassNotFindException : " + config, e);
				}

				strategy.setSharding(config);
				
				strategyConfigHolder.set(config);

				break;
			}
		}
		return get();
	}

	public static StrategyConfig get() {
		return strategyConfigHolder.get();
	}

	private static String convert(String name) {
		name = StringUtils.trim(name).replaceAll("`", "");
		return StringUtils.split(name, " ")[0];
	}

	@SuppressWarnings("unchecked")
	public static Object getParamValue(Object params, StrategyConfig config) {
		Object value = null;
		if (params instanceof Number || params instanceof String) {
			value = params;
		} else if (params instanceof Map) {
			Map<String, Object> map = (Map<String, Object>) params;
			
			value = map.get(config.getColumn());
		} else {
			value = ReflectUtil.getFieldValue(params, config.getColumn());
		}

		if (value == null) {
			value = params;
		}
		return value;
	}
}
