package com.liuhao.common.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CacheMap<K, V> {

	private ConcurrentMap<K, V> cache = new ConcurrentHashMap<K, V>();

	public V get(K k) {
		return get(k, null);
	}

	public V get(K k, V def) {
		V v = cache.get(k);
		if (v == null) {
			v = def;
		}
		return v;
	}

}
