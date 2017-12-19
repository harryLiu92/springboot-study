package com.liuhao.spi.api;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  @author  liuhao 
 *  @time    2017年9月20日
 *  @comment SPI
 */
public class SPIBuilder {
	
    private static Logger logger = LoggerFactory.getLogger(SPIBuilder.class);

    
    private static final String SPI_PATH = "SPI";
	/**
	 * 接口的全类名获取里面的key->子类
	 */
	private static final ConcurrentHashMap<Class, Map<String, Class>> spiCache = new ConcurrentHashMap<Class, Map<String, Class>>();

	protected static Map<String, Class> getSpiMap(Class clazz) {
	    Map<String, Class> map = spiCache.get(clazz);
	    return map;
	}

	protected static Class getSpiClass(Class clazz, String name) {
	    Map<String, Class> map = spiCache.get(clazz);
	    if (map != null) {
	        return map.get(name);
	    }
	    return null;
	}

	public static void start() {
		try {
		    URL url = Thread.currentThread().getContextClassLoader().getResource(SPI_PATH);
		    
		    if (url == null) {
		        logger.info("SPIBuilder.ERROR=========spiDir:" + url);
		    }
		    
			File spiDir = new File(url.getPath());

			logger.info("SPIBuilder.File=========spiDir:" + spiDir.getPath());
			
			if (spiDir.isDirectory()) {
				for (File namespace : spiDir.listFiles()) {
					for (File spi : namespace.listFiles()) {
						
						BufferedReader reader = new BufferedReader(new FileReader(spi));
						
						String line = null;
						
						Map<String, Class> spiMap = Maps.newHashMap();

						while ((line = reader.readLine()) != null) {
							String[] spiInfo = StringUtils.split(line, "=");
							
							String key = spiInfo[0].trim();
	
							String value = spiInfo[1].trim();

							spiMap.put(key, Class.forName(value));
							logger.info("SPIBuilder=========put:" + key + "=" + value);
						}
						spiCache.put(Class.forName(spi.getName()), spiMap);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
