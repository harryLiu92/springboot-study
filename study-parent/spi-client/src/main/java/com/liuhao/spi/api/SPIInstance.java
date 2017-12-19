package com.liuhao.spi.api;

import com.google.common.collect.Sets;
import com.liuhao.spi.annotation.SPI;
import com.liuhao.spi.annotation.SPIAdaptive;
import com.liuhao.spi.core.SPILoader;
import com.liuhao.spi.util.ClazzUtil;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hao liu
 */
public class SPIInstance<T> {

    private static final ConcurrentHashMap<Class, Object> interfaceCache = new ConcurrentHashMap<Class, Object>();

    private static ReentrantLock lock = new ReentrantLock();
    
	Class<T> clazz;
	
	SPI spi;

	public SPIInstance(Class<T> clazz) {
		this.clazz = clazz;
		this.spi = clazz.getAnnotation(SPI.class);
		
		if (spi == null) {
		    throw new RuntimeException(" 接口SPI注解不存在 ");
		}
	}

	public Set<String> keySet() {
	    Map<String, Class> map = SPIBuilder.getSpiMap(clazz);
	    
	    if (map == null ) {
	        return Sets.newHashSet();
	    }
	    
	    return map.keySet();
	}

	public T getDefaultExtension() {
		try {
			String spiName = spi.name();
			return createInstance(spiName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public T getExtension(String spiName) {
		try {
			return createInstance(spiName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private T createInstance(String spiName) throws Exception {
		
		Class<T> target = SPIBuilder.getSpiClass(clazz, spiName);
		
		return target.newInstance();
	}

	public T getAdapteExtension() {
		T t = null;
		try {
		    lock.lock();

		    if (interfaceCache.get(clazz) != null) {
		        
		        t = (T) interfaceCache.get(clazz);

		        return t;
		    }

			Class<T> tClazz = makeClass();

			t = tClazz.newInstance();

			interfaceCache.put(clazz, t);

		} catch (Exception e) {
		    
			throw new RuntimeException("getAdapteExtension动态编译异常", e);
			
		} finally {
		    lock.unlock();
		}
		return t;
	}

	private Class<T> makeClass() throws Exception {
		//ClassPool：CtClass对象的容器  
        ClassPool pool = ClassPool.getDefault();  

        //通过ClassPool新类名
        String pkgName = SPILoader.class.getName();
        pkgName = pkgName.substring(0, pkgName.lastIndexOf("."));
        CtClass ctClass = pool.makeClass(pkgName + "." + clazz.getSimpleName() + "$Adaptive");  

        // import
        pool.importPackage(pkgName);

		// interface
		ctClass.addInterface(pool.get(clazz.getName()));

		// method
		Method[] methods = clazz.getDeclaredMethods();
		
		String methodSource = makeMethodSource(methods);
		
		ctClass.addMethod(CtNewMethod.make(methodSource, ctClass));

//		 解冻不然frozen
//		ctClass.defrost(); 

		// 获取类的class文件
//		byte[] byteArr = ctClass.toBytecode(); 
//
//		String s = new String(byteArr);
//
//		String path = "C://Users//Administrator//Desktop//test.class";
//        FileOutputStream fos = new FileOutputStream(new File(path));  
//        fos.write(byteArr);  
//        fos.close();  
		return ctClass.toClass();
	}

	private String makeMethodSource(Method[] methods) {
		StringBuilder source = new StringBuilder();
		
		for (Method method : methods) {
			String methodName = method.getName();
			
			if ("equals".equals(methodName)
					|| "toString".equals(methodName)
						|| "hashCode".equals(methodName)) {
				continue;
			}

			String modifier = ClazzUtil.assembleMethodModifier(method);
			
			String returnType = ClazzUtil.assembleMethodReturn(method);
			
			String[] params = ClazzUtil.assembleMethodParams(method);

			String exception = ClazzUtil.assembleMethodException(method);
			
			StringBuilder methodSource = new StringBuilder();
			
			methodSource.append("\n")
						.append(modifier)
						.append("  ")
						.append(returnType)
						.append("  ")
						.append(methodName)
						.append("  ")
						.append(params[0])
						.append("  ")
						.append(exception)
						.append(" {");

			if (method.getAnnotation(SPIAdaptive.class) == null) {
				methodSource.append("\n throw new NoSuchMethodException('SPI not support this method, add SPIMethod Annotation'); ");
			} else {
				methodSource.append("\n" + clazz.getName() + " obj = ("+clazz.getName()+")SPILoader.getInstance("+clazz.getName()+".class).getExtension(args0.getName());");
				methodSource.append("\n obj." + methodName + params[1] + ";");
			}
			
			methodSource.append("\n}");
			source.append(methodSource.toString());
		}
		return source.toString();
	}

}
