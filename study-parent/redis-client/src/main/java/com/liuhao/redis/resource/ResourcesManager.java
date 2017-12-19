package com.liuhao.redis.resource;

import java.util.HashMap;
import java.util.Map;

public class ResourcesManager<T> {

	private ThreadLocal<Map<String, T>> __resources__context__ = new ThreadLocal<Map<String, T>>();
	private ResourceFactory<T> __resource__factory__;

	public ResourcesManager(ResourceFactory<T> resourceFactory) {
		this.__resource__factory__ = resourceFactory;
	}

	public T get(String name) {
		Map<String, T> container = getResources();
		T resource = container.get(name);
		if (resource == null) {
			resource = this.__resource__factory__.getResource(name);
			if (resource != null)
				container.put(name, resource);
		}
		return resource;
	}

	public Map<String, T> getResources() {
		Map<String, T> container = (Map<String, T>) this.__resources__context__.get();
		if (container == null) {
			container = new HashMap<String, T>(8, 1.0F);
			this.__resources__context__.set(container);
		}
		return container;
	}

	public void close() {
		Map<String, T> container = getResources();
		for (String name : container.keySet()) {
			T resource = container.get(name);
			if (resource != null) {
				try {
					this.__resource__factory__.close(name, resource);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		container.clear();
	}

	public static abstract interface ResourceFactory<T> {
		public abstract T getResource(String paramString);

		public abstract void close(String paramString, T paramT);
	}
}