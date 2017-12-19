package com.liuhao.spi.core;

import com.liuhao.spi.annotation.SPI;
import com.liuhao.spi.api.SPIInstance;

import java.lang.annotation.Annotation;

/**
 * @author liuhao
 * 
 */
public class SPILoader {
	
	public static <T> SPIInstance<T> getInstance(Class<T> clazz) {

		Annotation spi = clazz.getAnnotation(SPI.class);
		
		if (spi == null) {
			throw new RuntimeException(" interface SPI Annotation not exist ");
		}
		
		return new SPIInstance<T>(clazz);
	}
}
