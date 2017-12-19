package com.liuhao.spi.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author liuhao
 * 封装name
 * 作为SPI adaptive必要的参数
 */
public class SPIRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public static SPIRequest name(String name) {
	    SPIRequest req = new SPIRequest();
	    req.setName(name);
	    return req;
	}

	private SPIRequest() {}

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
