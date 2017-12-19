package com.liuhao.user.spi;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

import org.hibernate.AnnotationException;

import com.liuhao.user.spi.test.SPITest;

/**
 * @author harry.liu
 * 
 */
public class SPILoader {
	
    private static final Pattern IMPORT_PATTERN = Pattern.compile("import\\s+([\\w\\.\\*]+);\n");
	
	public static <T> SPIInstance <T> getInstance(Class<T> clazz) {

		Annotation spi = clazz.getAnnotation(SPI.class);
		
		if (spi == null) {
			throw new AnnotationException(" interface SPI Annotation not exist ");
		}
		
		return new SPIInstance<T>(clazz);
	}

	public static void main(String[] args) throws Exception {
		SPIBuilder.loadClass();
		getInstance(SPITest.class).getAdapteExtension().setName(new SPIRequest("large"), "hh");
	}
}
