package com.liuhao.user.spi;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;


public class AssmebleClazzHepler {

	public static String assembleMethodModifier(Method method) {
		
		if (method.getModifiers() == Modifier.PUBLIC) { return "public"; }
		if (method.getModifiers() == Modifier.PROTECTED) { return "protected"; }
		if (method.getModifiers() == Modifier.PRIVATE) { return "private"; }
		
		return "public";
	}

	public static String assembleMethodReturn(Method method) {
		return method.getReturnType().getCanonicalName();
	}

	public static String[] assembleMethodParams(Method method) {
		Class<?>[] paramClazzs = method.getParameterTypes();
		
		List<String> list0 = Lists.newArrayList();
		List<String> list1 = Lists.newArrayList();
		int count = 0;
		
		if (! (paramClazzs.length > 0 && SPIRequest.class.isAssignableFrom(paramClazzs[0]))) {
			throw new RuntimeException(method.getName()+"不包含SPIRequest对象参数,作为第一个参数");
		}
		
		for (Class<?> pClazz : paramClazzs) {
			String str0 = pClazz.getName() + " args" + count;
			String str1 = " args" + count;
			list0.add(str0);
			list1.add(str1);
			
			count ++;
		}
		
		String[] params = new String[] {"(" + StringUtils.join(list0, ",") + ")", "(" + StringUtils.join(list1, ",") + ")"};
		
		return params;
	}

	public static String assembleMethodException(Method method) {
		Class<?>[] exps = method.getExceptionTypes();
		
		StringBuilder codeBuidler = new StringBuilder();
        if (exps.length > 0) {
            codeBuidler.append(" throws ");
            for (int i = 0; i < exps.length; i ++) {
                if (i > 0) {
                	codeBuidler.append(", ");
                }
                codeBuidler.append(exps[i].getCanonicalName());
            }
        }
        
        return codeBuidler.toString();
	}
	
    public static Class<?> forName(String[] packages, String className)  {
        try {
            return _forName(className);
        } catch (ClassNotFoundException e) {
            if (packages != null && packages.length > 0) {
                for (String pkg : packages) {
                    try {
                        return _forName(pkg + "." + className);
                    } catch (ClassNotFoundException e2) {
                    }
                }
            }
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
    
    public static Class<?> forName(String className) {
        try {
            return _forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
    
    public static Class<?> _forName(String className) throws ClassNotFoundException {
        if ("boolean".equals(className))
            return boolean.class;
        if ("byte".equals(className))
            return byte.class;
        if ("char".equals(className))
            return char.class;
        if ("short".equals(className))
            return short.class;
        if ("int".equals(className))
            return int.class;
        if ("long".equals(className))
            return long.class;
        if ("float".equals(className))
            return float.class;
        if ("double".equals(className))
            return double.class;
        if ("boolean[]".equals(className))
            return boolean[].class;
        if ("byte[]".equals(className))
            return byte[].class;
        if ("char[]".equals(className))
            return char[].class;
        if ("short[]".equals(className))
            return short[].class;
        if ("int[]".equals(className))
            return int[].class;
        if ("long[]".equals(className))
            return long[].class;
        if ("float[]".equals(className))
            return float[].class;
        if ("double[]".equals(className))
            return double[].class;
        try {
            return arrayForName(className);
        } catch (ClassNotFoundException e) {
            if (className.indexOf('.') == -1) { // 灏濊瘯java.lang鍖�
                try {
                    return arrayForName("java.lang." + className);
                } catch (ClassNotFoundException e2) {
                    // 蹇界暐灏濊瘯寮傚父, 鎶涘嚭鍘熷寮傚父
                }
            }
            throw e;
        }
    }
    
    private static Class<?> arrayForName(String className) throws ClassNotFoundException {
        return Class.forName(className.endsWith("[]")
                ? "[L" + className.substring(0, className.length() - 2) + ";"
                        : className, true, Thread.currentThread().getContextClassLoader());
    }
}
