package com.liuhao.user.spi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

public class SPIBuilder {
	
	/**
	 * 接口的全类名.name
	 */
	private static final ConcurrentHashMap<String, Class> spiCache = new ConcurrentHashMap<String, Class>();

	private static final String SPI_PATH = "SPI";

	public static Class getSpiCache(String name) {
		return spiCache.get(name);
	}

	public static void loadClass() {
		try {
			
			String path = SPIBuilder.class.getClassLoader().getResource(SPI_PATH).getPath();
			File spiDir = new File(path);
			if (spiDir.isDirectory()) {
				for (File namespace : spiDir.listFiles()) {
					for (File spi : namespace.listFiles()) {
						
						BufferedReader reader = new BufferedReader(new FileReader(spi));
						
						String line = null;
						
							while ((line = reader.readLine()) != null) {
								String[] spiInfo = StringUtils.split(line, "=");
								
								String key = spiInfo[0].trim();
		
								String value = spiInfo[1].trim();
								
								spiCache.put(key, Class.forName(value));
							}
						}
					}
				}
			for (Entry<String, Class> entry : spiCache.entrySet()) {
				System.out.println(entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("start");
		SPIBuilder.loadClass();
		for (Entry<String, Class> entry : spiCache.entrySet()) {
			System.out.println(entry);
		}
		System.out.println("end");
	}
}
