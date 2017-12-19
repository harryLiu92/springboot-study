//package com.liuhao.dubbo.factory;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Map;
//import java.util.Properties;
//
//import org.apache.commons.lang3.StringUtils;
//
//import com.alibaba.dubbo.config.ApplicationConfig;
//
///**
// *  @author  liuhao 
// *  @time    2017年7月7日
// *  @comment Dubbo消费者API方式
// */
//public class DubboFactory {
//
//// // 当前应用配置
////    ApplicationConfig application = new ApplicationConfig();
////    application.setName("yyy");
////     
////    // 连接注册中心配置
////    RegistryConfig registry = new RegistryConfig();
////    registry.setAddress("10.20.130.230:9090");
////    registry.setUsername("aaa");
////    registry.setPassword("bbb");
////     
////    // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
////     
////    // 引用远程服务
////    ReferenceConfig<XxxService> reference = new ReferenceConfig<XxxService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
////    reference.setApplication(application);
////    reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
////    reference.setInterface(XxxService.class);
////    reference.setVersion("1.0.0");
////     
////    // 和本地bean一样使用xxxService
////    XxxService xxxService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
//    
//
//    private static String DEFAULT_DUBBO_FILE = "dubbo.properties";
//    
//    private static ApplicationConfig applicationConfig ;
//    
//    private static boolean init = false;
//    
//    static {
//        if (! init) {
//            start();
//        }
//        init = true;
//    }
//
//    private static void start() {
//        Map<String, String> prop = initProperties(DEFAULT_DUBBO_FILE);
//        
//        DubboReference reference = createDubboBean(prop);
//        
//        checkProp(reference);
//        
//        initDubbo(reference);
//    }
//
//    private static DubboReference createDubboBean(Map<String, String> prop) {
//        String registryProtocol = prop.get("dubbo.registry.protocol");
//        String registryAddress = prop.get("dubbo.registry.address");
//        String applicationName = prop.get("dubbo.application.name");
//        
//        return new DubboReference(registryProtocol, registryAddress, applicationName);
//    }
//
//    private static void checkProp(DubboReference reference) {
//        String registryProtocol = reference.getRegistryProtocol().get("dubbo.registry.protocol");
//        String registryAddress = prop.get("dubbo.registry.address");
//        String applicationName = prop.get("dubbo.application.name");
//        
//        if (StringUtils.isBlank(registryProtocol) 
//                || StringUtils.isBlank(registryAddress)
//                    || StringUtils.isBlank(applicationName)) {
//            throw new IllegalArgumentException("registryProtocol|registryAddress|applicationName is blank! ");
//        }
//    }
//
//    private static Map<String, String> initProperties(String propertiesName) {
//        Map<String, String> prop = PropertiesUtils.getProperties(propertiesName);
//        
//        return prop;
//    }
//
//    private static void initDubbo(DubboReference reference) {
//        applicationConfig = new ApplicationConfig();
////        applicationConfig.setName(name);
//        
//    }
//}
