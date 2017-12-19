package com.liuhao.user.spi.test;

import com.liuhao.user.spi.SPI;
import com.liuhao.user.spi.SPIAdaptive;
import com.liuhao.user.spi.SPIRequest;

@SPI(name="impl")
public interface SPITest {

	@SPIAdaptive
	void setName(SPIRequest req, String name) throws Exception;
}
