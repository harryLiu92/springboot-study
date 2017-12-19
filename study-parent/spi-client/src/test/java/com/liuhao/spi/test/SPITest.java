package com.liuhao.spi.test;


import com.liuhao.spi.annotation.SPI;
import com.liuhao.spi.annotation.SPIAdaptive;
import com.liuhao.spi.api.SPIRequest;

@SPI(name = "impl")
public interface SPITest {

	@SPIAdaptive
	void setName(SPIRequest req, String name) throws Exception;
}
