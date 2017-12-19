package com.liuhao.user;

public class SpiAImpl implements Spi {

	public boolean isSupport(String name) {
		return "SPIA".equalsIgnoreCase(name.trim());

	}

	@Override
	public String sayHello() {
	    return "hello 我是厂商A";
	}
}