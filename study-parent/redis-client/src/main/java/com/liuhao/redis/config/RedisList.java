package com.liuhao.redis.config;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

@XmlRootElement(name = "list")
public class RedisList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<RedisConfig> redis;

	@XmlElement(name = "redis")
	public List<RedisConfig> getRedis() {
		return redis;
	}

	public void setRedis(List<RedisConfig> redis) {
		this.redis = redis;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
