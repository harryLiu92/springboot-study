package com.liuhao.user.spi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Administrator
 * 
 * 使用SPI
 * 1,扫描/META-INF/services下面文件
 * 2,初始化加载
 * 3,三个方法getAdaptExtension,getDefaultExtension,getExtension
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SPI {
	
	String name() default StringUtils.EMPTY;
}
