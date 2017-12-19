package com.liuhao.user.spi;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author liuhao
 * 封装clazz和name
 * 作为SPI adaptive必要的参数
 */
public class SPIRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public SPIRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
	return ToStringBuilder.reflectionToString(this);
	}
}
