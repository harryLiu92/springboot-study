package com.liuhao.spi.test;


import com.liuhao.spi.api.SPIRequest;

public class SPIImpl implements SPITest{

	@Override
	public void setName(SPIRequest req, String name) throws Exception {
		System.out.println("SPIImpl.hello");
	}

}

