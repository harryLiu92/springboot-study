package com.liuhao.redis.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liuhao.common.utils.CollectionUtil;
import com.liuhao.common.utils.CommonUtils;
import com.liuhao.common.utils.ReflectUtil;
import com.liuhao.redis.config.RedisLocation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.*;

public class RedisClientProxy implements RedisClientApi, InvocationHandler {

	Logger logger = LoggerFactory.getLogger(RedisClientProxy.class);

	private static Charset UTF8 = Charset.forName("UTF-8");

	private static Map<String, Method> proxyApiMethodMap = new HashMap();
	private RedisLocation location;
	private Jedis jedis;

	public RedisClientProxy(RedisLocation location) {
		this.location = location;
	}

	public void close() {
		IOUtils.close(jedis);
	}

	protected JedisPool getJedisPool() {
		JedisPool pool = null;
		if (location != null) {
			pool = location.getJedisPool();
		}
		return pool;
	}

	private Jedis getJedis() {
		if ((jedis != null))
			close();

		if (jedis == null) {
			try {
				jedis = ((Jedis) getJedisPool().getResource());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return jedis;
	}

	public void putObject(String key, Object value) {
		if (value == null)
			throw new NullPointerException("Unspported bean=null");
		String strValue = JSON.toJSONString(value);
		getJedis().set(key, strValue);
	}

	public <T> T getObject(String key, Class<T> clazz) {

		String value = getJedis().get(key);

		return (T) (StringUtils.isEmpty(value)? null : JSONObject.parseObject(value, clazz));
	}

	public void putList(String key, List<Object> list) {
		List<String> values = Lists.newArrayList();

		for (Object obj : list) {
			String value = JSON.toJSONString(obj);
			values.add(value);
		}

		getJedis().rpush(key, values.toArray(new String[]{}));
	}

	public <T> List<T> queryList(String key, Class<T> clazz, long start, long end) {

		List<String> list = getJedis().lrange(key, start, end);

		List<T> resultList = Lists.newArrayList();

		for (String str : list) {
			T t = JSONObject.parseObject(str, clazz);
			resultList.add(t);
		}

		return resultList;
	}

	public void putMap(String key, Map<String, Object> map) {
		Map<String, String> strMap = Maps.newHashMap();

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String value = JSON.toJSONString(entry.getValue());
			strMap.put(entry.getKey(), value);
		}

		getJedis().hmset(key, strMap);
	}

	public <T> Map<String, T> getMap(String key, Class<T> clazz, String... fields) {

		Map<String, T> resultMap = Maps.newHashMap();

		Jedis jedis = getJedis();

		for (String field : fields) {
			String value = jedis.hget(key, field);

			T t = JSONObject.parseObject(value, clazz);

			resultMap.put(field, t);
		}

		return resultMap;
	}

	public <T> Map<String, T> getMap(String key, Class<T> clazz) {

		Map<String, T> resultMap = Maps.newHashMap();

		Jedis jedis = getJedis();

		Set<String> fields = jedis.hkeys(key);

		return getMap(key, clazz, fields.toArray(new String[]{}));
	}

	public <T> List<T> popObject(String key, Class<T> clazz) {
		List<T> list = Lists.newArrayList();

		Jedis jedis = getJedis();

		long length = jedis.llen(key);

		for (long i = 1; i < length; i++) {

			String value = jedis.lpop(key);

			T t = JSONObject.parseObject(value, clazz);

			list.add(t);
		}

		return list;
	}

	public Transaction getTransaction() {
		return getJedis().multi();
	}

	// -----------------------------------------------------------------

	public long setAdd(String key, Object value) {
		return getJedis().sadd(CommonUtils.parserObject(key), new byte[][] { CommonUtils.parserObject(value) })
				.longValue();
	}

	public <T> List<T> sorting(Class<T> klass, String key, SortingParams sorting) {
		List<byte[]> byteList = getJedis().sort(CommonUtils.parserObject(key), sorting);
		List<T> result = new ArrayList<T>(byteList.size());
		for (byte[] data : byteList) {
			T e = JSONObject.parseObject(data, klass);
			result.add(e);
		}
		return result;
	}

	public List<?> checkAndSet(String key, Object value) {
		return cas(key, value, -1);
	}

	public List<?> checkAndSetExpire(String key, Object value, int seconds) {
		return cas(key, value, seconds);
	}

	public void putObjectExpire(String key, Object value, int seconds) {
		byte[] k = CommonUtils.parserObject(key);
		byte[] v = CommonUtils.parserObject(value);
		getJedis().setex(k, seconds, v);
	}

	public long sortedsetAdd(String key, Object value, double score) {
		Long success = getJedis().zadd(CommonUtils.parserObject(key), score, CommonUtils.parserObject(value));
		return success == null ? 0L : success.longValue();
	}

	public long sortedsetRem(String key, Object value) {
		Long success = getJedis().zrem(CommonUtils.parserObject(key), new byte[][] { CommonUtils.parserObject(value) });
		return success == null ? 0L : success.longValue();
	}

	public <E> List<E> sortedsetRange(String key, int start, int end, Class<E> klass) {
		Set<byte[]> set = getJedis().zrange(CommonUtils.parserObject(key), start, end);
		List<E> list = Lists.newArrayList();
		if (!CollectionUtil.isEmpty(set)) {
			list = new ArrayList<E>(set.size());
			for (byte[] bytes : set) {
				E e = JSONObject.parseObject(bytes, klass);
				list.add(e);
			}
		}
		return list;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			Method impl = (Method) proxyApiMethodMap.get(method.getName());
			return impl != null ? handleProxyApiMethod(impl, args) : handleJedisMethod(method, args);
		} catch (Throwable t) {
			if (isJedisConnectionException(t)) {
				close();
			} else {
				close();
			}
			throw new Throwable(t);
		}
	}

	private static boolean isJedisConnectionException(Throwable t) {
		Throwable cause = t.getCause();
		if (cause == null)
			return false;
		if ((cause instanceof JedisConnectionException)) {
			return true;
		}
		return isJedisConnectionException(cause);
	}

	private Object handleProxyApiMethod(Method method, Object[] args) throws Throwable {
		return method.invoke(this, args);
	}

	private Object handleJedisMethod(Method method, Object[] args) throws Throwable {
		return method.invoke(getJedis(), args);
	}

	private List<?> cas(String key, Object value, int seconds) {
		byte[] k = CommonUtils.parserObject(key);
		byte[] v = CommonUtils.parserObject(value);
		for (int index = 0; index < 10; index++) {
			getJedis().watch(new byte[][] { k });
			Transaction t = getJedis().multi();

			if (seconds <= 0)
				t.set(k, v);
			else {
				t.setex(k, seconds, v);
			}
			List result = t.exec();
			if (result != null)
				return result;
		}
		return null;
	}

	private static void init() {
		Method[] methods = RedisClientApi.class.getMethods();
		for (Method m : methods) {
			Method impl = ReflectUtil.getTargetMethod(RedisClientProxy.class, m.getName(), m.getParameterTypes());
			proxyApiMethodMap.put(m.getName(), impl);
		}
	}

	static {
		init();
	}

}