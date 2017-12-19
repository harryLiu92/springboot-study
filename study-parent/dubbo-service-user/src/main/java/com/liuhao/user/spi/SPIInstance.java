package com.liuhao.user.spi;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import javassist.LoaderClassPath;

/**
 * @author hao liu
 */
public class SPIInstance<T> {

    private static final Pattern IMPORT_PATTERN = Pattern.compile("import\\s+([\\w\\.\\*]+);\n");

    private static final Pattern EXTENDS_PATTERN = Pattern.compile("\\s+extends\\s+([\\w\\.]+)[^\\{]*\\{\n");

    private static final Pattern IMPLEMENTS_PATTERN = Pattern.compile("\\s+implements\\s+([\\w\\.]+)\\s*\\{\n");
    
    private static final Pattern METHODS_PATTERN = Pattern.compile("\n(private|public|protected)\\s+");

    private static final Pattern FIELD_PATTERN = Pattern.compile("[^\n]+=[^\n]+;");
    
	T t;
	
	Class<T> clazz;
	
	SPI spi;
	
	AtomicInteger count;

	SPIInstance(Class<T> clazz) {
		this.clazz = clazz;
		this.spi = clazz.getAnnotation(SPI.class);
		this.count = new AtomicInteger();
	}

	public T getDefaultExtension() {
		try {
			String spiName = spi.name();
			return createDefaultInstance(spiName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public T getExtension(String spiName) {
		try {
			return createDefaultInstance(spiName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private T createDefaultInstance(String spiName) throws Exception {
		
//		String fullName = clazz.getName() + "." + spiName;
		Class<T> target = SPIBuilder.getSpiCache(spiName);
		
		return target.newInstance();
	}

	public T getAdapteExtension() {
		T t = null;
		try {

			Class<T> tClazz = makeClass();
			
			t = tClazz.newInstance();

		} catch (Exception e) {
			throw new RuntimeException("getAdapteExtension动态编译异常", e);
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
		
		byte[] byteArr = ctClass.toBytecode(); 

		String s = new String(byteArr);

		String path = "C://Users//Administrator//Desktop//test.class";
        FileOutputStream fos = new FileOutputStream(new File(path));  
        fos.write(byteArr);  
        fos.close();  
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

			String modifier = AssmebleClazzHepler.assembleMethodModifier(method);
			
			String returnType = AssmebleClazzHepler.assembleMethodReturn(method);
			
			String[] params = AssmebleClazzHepler.assembleMethodParams(method);

			String exception = AssmebleClazzHepler.assembleMethodException(method);
			
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

	@SuppressWarnings("unchecked")
	private Class<T> makeClass(String source) throws Exception {
		CtClass cls;
		String name = clazz.getName();
		int i = name.lastIndexOf('.');
		String className = i < 0 ? name : name.substring(i + 1);
		
		ClassPool pool = new ClassPool(true);
		
		pool.appendClassPath(new LoaderClassPath(getClass().getClassLoader()));
		
        Matcher matcher = IMPORT_PATTERN.matcher(source);
        List<String> importPackages = new ArrayList<String>();
        Map<String, String> fullNames = new HashMap<String, String>();
        while (matcher.find()) {
            String pkg = matcher.group(1);
            if (pkg.endsWith(".*")) {
                String pkgName = pkg.substring(0, pkg.length() - 2);
                System.out.println("pkgName1:" + pkgName);
                pool.importPackage(pkgName);
                importPackages.add(pkgName);
            } else {
                String pkgName = pkg;
                pool.importPackage(pkgName);
                importPackages.add(pkgName);
                System.out.println("pkgName2:" + pkgName);
            }
            System.out.println("iiii:"+importPackages);
        }
        String[] packages = importPackages.toArray(new String[0]);
        matcher = EXTENDS_PATTERN.matcher(source);
        if (matcher.find()) {
            String extend = matcher.group(1).trim();
            String extendClass;
            if (extend.contains(".")) {
                extendClass = extend;
            } else if (fullNames.containsKey(extend)) {
                extendClass = fullNames.get(extend);
            } else {
                extendClass = AssmebleClazzHepler.forName(packages, extend).getName();
            }
            cls = pool.makeClass(name, pool.get(extendClass));
        } else {
        	System.out.println("extends:"+name);
            cls = pool.makeClass(name);
        }
        matcher = IMPLEMENTS_PATTERN.matcher(source);
        if (matcher.find()) {
            String[] ifaces = matcher.group(1).trim().split("\\,");
            for (String iface : ifaces) {
                iface = iface.trim();
                String ifaceClass;
                if (iface.contains(".")) {
                    ifaceClass = iface;
                } else if (fullNames.containsKey(iface)) {
                    ifaceClass = fullNames.get(iface);
                } else {
                    ifaceClass = AssmebleClazzHepler.forName(packages, iface).getName();
                }
                System.out.println("ifaceClass:"+ifaceClass);
                cls.addInterface(pool.get(ifaceClass));
            }
        }
        String body = source.substring(source.indexOf("{") + 1, source.length() - 1);
        String[] methods = METHODS_PATTERN.split(body);
        for (String method : methods) {
            method = method.trim();

            if (method.length() > 0) {
                if (method.startsWith(className)) {
                    cls.addConstructor(CtNewConstructor.make("public " + method, cls));
                } else if (FIELD_PATTERN.matcher(method).matches()) {
                    cls.addField(CtField.make("private " + method, cls));
                } else {
                	System.out.println("method:"+method);
                    cls.addMethod(CtNewMethod.make("public " + method, cls));
                }
            }
        }
		
        return cls.toClass(getClass().getClassLoader(), null);
	}

	private String makeSource() {

		String name = clazz.getName();
		
		name = name.substring(name.lastIndexOf(".")+1, name.length());

		StringBuilder source = new StringBuilder();

//		String newName = name+"$Adaptive"+count.getAndIncrement();

		source.append("package com.liuhao.user.spi;\r\n");
		source.append("\nimport " + SPILoader.class.getName() + ";");
		source.append("\npublic class " + clazz.getSimpleName() + "$Adaptive" + " implements  " + clazz.getCanonicalName() + "{");

		Method[] methods = clazz.getDeclaredMethods();
		
		source.append(makeMethodSource( methods));
		
		source.append("\n}");
		return source.toString();
	}
}
