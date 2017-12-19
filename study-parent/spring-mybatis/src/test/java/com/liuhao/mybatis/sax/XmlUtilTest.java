package com.liuhao.mybatis.sax;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import com.liuhao.common.utils.XmlUtil;
import com.liuhao.mybatis.entity.User;
import com.liuhao.mybatis.entity.Users;
import com.liuhao.mybatis.sharding.config.ShardingConfig;
import com.liuhao.mybatis.sharding.config.StrategyConfig;

public class XmlUtilTest {
	
	@Test
	public void testBean1() throws Exception {

        InputStream input = XmlUtil.class.getClassLoader().getResourceAsStream("user.xml");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder txt = new StringBuilder();
        
        String line = "";
        while ((line = reader.readLine()) != null) {
       	 txt.append(line).append("\n");
        }
        
        Users us = XmlUtil.parseToJavaBean(txt.toString(), Users.class);
        
        for (User u : us.getUsers()) {
        	System.out.println(u);
        }
        
	}
	
	@Test
	public void testBean2() throws Exception {
		
		InputStream input = XmlUtil.class.getClassLoader().getResourceAsStream("sharding-config.xml");
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		StringBuilder txt = new StringBuilder();
		
		String line = "";
		while ((line = reader.readLine()) != null) {
			txt.append(line).append("\n");
		}
		
		ShardingConfig us = XmlUtil.parseToJavaBean(txt.toString(), ShardingConfig.class);
		
		for (StrategyConfig u : us.getStrategyConfigs()) {
			System.out.println(u);
		}
		
	}
}
