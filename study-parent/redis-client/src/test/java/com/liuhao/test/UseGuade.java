package com.liuhao.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liuhao.common.utils.CollectionUtil;
import com.liuhao.common.utils.CommonUtils;
import com.liuhao.redis.client.RedisClient;
import com.liuhao.redis.client.RedisClientProxy;
import com.liuhao.redis.factory.RedisClientFactory;

public class UseGuade {
	private long id;
	private String name = "liuhao";

	@Test
	public void  rpush() {
		String queue = "queue"; 
		UseGuade guade1 = new UseGuade();
		guade1.setId(1L);
		UseGuade guade2 = new UseGuade();
		guade2.setId(2L);
		Object[] values = new Object[] {guade1, guade2};

		if (!CollectionUtil.isEmpty(values)) {
			byte[] key = CommonUtils.parserObject(queue);
			List<byte[]> list = Lists.newArrayList();

			for (int i = 0; i < values.length; i++) {
				Object e = values[i];
				if (e != null) {
					byte[] bytes = CommonUtils.parserObject(e);
					list.add(bytes);
				}
			}
			byte[][] array = list.toArray(new byte[][]{});
			System.out.println(key);
			System.out.println(array);
			Map<byte[], byte[][]> map = Maps.newHashMap();
			map.put(key, array);
			System.out.println(map);
		}

	}

	public static void main(String[] args) {
		String redis_name = "demo";

		try {

			RedisClient proxy = RedisClientFactory.getClient("liuhao");
			//
			System.out.println(proxy.set("test1", "111"));
			System.out.println(proxy.get("liuhao"));

			UseGuade guade = new UseGuade();
			guade.id = 100L;

			// proxy.lpush("l1", new String[] {"11", "22", "33"});
//			System.out.println(proxy.rpush("guade", new Object[] { guade }));
//			System.out.println("------------------------------");
//			UseGuade demo = (UseGuade) proxy.lpop("guade", UseGuade.class);
//			System.out.println(demo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisClientFactory.close();
		}
	}

	public String toString() {
		return "UseGuade [id=" + this.id + ", name=" + this.name + "]";
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
