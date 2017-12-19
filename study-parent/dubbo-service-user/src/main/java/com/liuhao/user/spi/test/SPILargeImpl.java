package com.liuhao.user.spi.test;

import com.liuhao.user.spi.SPIRequest;

public class SPILargeImpl implements SPITest{

	@Override
	public void setName(SPIRequest req, String name) throws Exception {
		System.out.println("SPILargeImpl.hello");
	}
}
