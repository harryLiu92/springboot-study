package com.liuhao.spi.test;


import com.liuhao.spi.api.SPIBuilder;
import com.liuhao.spi.api.SPIRequest;
import com.liuhao.spi.core.SPILoader;

public class SPIMain {

    public static void main(String[] args) throws Exception {
        SPIBuilder.start();

        SPILoader.getInstance(SPITest.class).getAdapteExtension().setName(SPIRequest.name("large"), "hh");

        SPILoader.getInstance(SPITest.class).getAdapteExtension().setName(SPIRequest.name("impl"), "aa");
    }
}
