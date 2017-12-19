package com.liuhao.redis.config;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

@XmlRootElement(name = "node")
public class RedisNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String host;

	private int port;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
