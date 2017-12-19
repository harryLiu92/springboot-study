package com.liuhao.mybatis.sharding.config;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @since  1.0.0
 * @author liuhao 
 * @date   2017年11月7日下午5:52:09
 */
@XmlRootElement(name = "sharding")
public class ShardingConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<StrategyConfig> strategyConfigs;
	
	public ShardingConfig() {
		super();
	}

	@XmlElement(name="strategy")
	public List<StrategyConfig> getStrategyConfigs() {
		return strategyConfigs;
	}

	public void setStrategyConfigs(List<StrategyConfig> strategyConfigs) {
		this.strategyConfigs = strategyConfigs;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
