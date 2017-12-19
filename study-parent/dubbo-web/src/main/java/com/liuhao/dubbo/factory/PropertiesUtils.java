package com.liuhao.dubbo.factory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public class PropertiesUtils {
    
    public static Map<String, String> getProperties(String propertiesName) {
        Properties prop = new Properties();
        File file = new File(propertiesName);
        Map<String, String> map = Maps.newHashMap();
        
        try {
            prop.load(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Object key : prop.keySet()) {
            if (key != null && StringUtils.isNotBlank(String.valueOf(key))) {
                String value = prop.getProperty(String.valueOf(key));
                map.put(String.valueOf(key), value);
                System.out.println(key + "||" + value);
            }
        }

        return map;
    }
}
