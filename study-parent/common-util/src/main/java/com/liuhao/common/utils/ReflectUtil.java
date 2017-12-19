package com.liuhao.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

public class ReflectUtil {

	public static Object getFieldValue(Object obj, String fieldName) {

		if (obj == null) {
			return null;
		}

		Field targetField = getTargetField(obj.getClass(), fieldName);

		try {
			return FieldUtils.readField(targetField, obj, true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Field getTargetField(Class<?> targetClass, String fieldName) {
		Field field = null;

		try {
			if (targetClass == null) {
				return field;
			}

			if (Object.class.equals(targetClass)) {
				return field;
			}

			field = FieldUtils.getDeclaredField(targetClass, fieldName, true);
			if (field == null) {
				field = getTargetField(targetClass.getSuperclass(), fieldName);
			}
		} catch (Exception e) {
		}

		return field;
	}

	public static void setFieldValue(Object obj, String fieldName, Object value) {
		if (null == obj) {
			return;
		}
		Field targetField = getTargetField(obj.getClass(), fieldName);
		try {
			FieldUtils.writeField(targetField, obj, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static Method getTargetMethod(Class<?> clazz, String methodName, Class<?> [] params) {
		Method targetMethod = null;

		if (StringUtils.isEmpty(methodName)) {
			return targetMethod;
		}
		try {
			boolean flag =true;
			while (flag) {

				targetMethod = clazz.getDeclaredMethod(methodName, params);

				if (targetMethod != null) {
					targetMethod.setAccessible(true);
					return targetMethod;
				}
				clazz = clazz.getSuperclass();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return targetMethod;
	}
}
