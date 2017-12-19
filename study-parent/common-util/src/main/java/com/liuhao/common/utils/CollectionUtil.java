package com.liuhao.common.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionUtil {

	public static boolean isEmpty(Map map) {
		return map == null || map.isEmpty();
	}

	public static boolean isEmpty(Set set) {
		return set == null || set.isEmpty();
	}

	public static boolean isEmpty(List list) {
		return list == null || list.isEmpty();
	}
	
	public static boolean isEmpty(Object[] objs) {
		return objs == null || objs.length == 0;
	}
}
