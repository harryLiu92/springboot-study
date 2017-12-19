package com.liuhao.mybatis.sharding.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.liuhao.common.utils.XmlUtil;
import com.liuhao.mybatis.sharding.config.ShardingConfig;

public class ShardingProvider {

	public static final String SHARDING_CONFIG = "sharding-config.xml";

	public static ShardingConfig shardingConfig = null;
	
	public static synchronized void start() {

		try {
			InputStream input = XmlUtil.class.getClassLoader().getResourceAsStream("sharding-config.xml");
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			StringBuilder txt = new StringBuilder();

			String line = "";

			while ((line = reader.readLine()) != null) {
				txt.append(line).append("\n");
			}

			shardingConfig = XmlUtil.parseToJavaBean(txt.toString(), ShardingConfig.class);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static ShardingConfig get() {
		if (shardingConfig == null) {
			start();
		}
		return shardingConfig;
	}
}
