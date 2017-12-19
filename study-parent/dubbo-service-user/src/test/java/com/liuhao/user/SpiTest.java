package com.liuhao.user;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiTest {

	public static void main(String[] args) {
		System.out.println("start");
		ServiceLoader<Spi> spiLoader = ServiceLoader.load(Spi.class);
		System.out.println(spiLoader.iterator().hasNext());
		
		Iterator<Spi> it = spiLoader.iterator();
		while (it.hasNext()) {
			Spi spi = it.next();
			System.out.println(spi);
			System.out.println(spi.sayHello());
		}
		System.out.println("complete");
	}
}
