package com.liuhao.common.utils;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;

public class CommonUtils {

	public static int parserInt(Object obj) {
		return parserInt(obj, 0);
	}

	public static int parserInt(Object obj, int def) {
		if (obj instanceof Number) {
			return ((Number)obj).intValue();
		} else {
			try {
				return Integer.parseInt(String.valueOf(obj));
			} catch (Exception e) {
			}
		}
		return def;
	}

	public static float parserFloat(Object obj) {
		return parserFloat(obj, 0);
	}

	public static float parserFloat(Object obj, int def) {
		if (obj instanceof Number) {
			return ((Number)obj).floatValue();
		} else {
			try {
				return Float.parseFloat(String.valueOf(obj));
			} catch (Exception e) {
			}
		}
		return def;
	}

	public static long parserLong(Object obj) {
		return parserLong(obj, 0);
	}

	public static long parserLong(Object obj, int def) {
		if (obj instanceof Number) {
			return ((Number) obj).longValue();
		} else {
			try {
				return Long.parseLong(String.valueOf(obj));
			} catch (Exception e) {
			}
		}
		return def;
	}

	public static byte[] parserObject(Object value) {
		if (value instanceof String) {
			String sValue = (String)value;
			return (StringUtils.isEmpty(sValue) ? "null" : sValue).getBytes();
		}
		return JSON.toJSONBytes(value, new SerializeConfig());
	}
}
