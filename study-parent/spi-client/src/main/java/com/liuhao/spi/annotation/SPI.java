package com.liuhao.spi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 * 
 * SPI使用在接口上提供默认实现name
 * 
 * 使用SPI
 * 1,扫描/SPI/命名空间/下面文件，用法同dubbo的SPI
 * 2,初始化加载
 * 3,三个方法getAdaptExtension,getDefaultExtension,getExtension
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SPI {
	
	String name();
}
