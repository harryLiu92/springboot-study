package com.liuhao.redis.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.resource.NotSupportedException;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.google.common.collect.Lists;
import com.liuhao.common.utils.CollectionUtil;
import com.liuhao.common.utils.CommonUtils;
import com.liuhao.redis.config.RedisLocation;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisClusterClientProxy implements RedisClientApi, InvocationHandler {

	Logger logger = LoggerFactory.getLogger(RedisClusterClientProxy.class);

	private static Charset UTF8 = Charset.forName("UTF-8");

	private static Map<String, Method> proxyApiMethodMap = new HashMap<String, Method>();
	private RedisLocation location;

	public RedisClusterClientProxy(RedisLocation location) {
		this.location = location;
	}

	public void close() {
		IOUtils.close(location.getCluster());
	}

	public void putObject(String key, Object value) {
		if (value == null)
			throw new NullPointerException("Unspported bean=null");
		String strValue = JSON.toJSONString(value);
		getJedisCluster().set(key, strValue);
	}

	public <T> T getObject(String key, Class<T> clazz) {

		String value = getJedisCluster().get(key);

		return (T) (StringUtils.isEmpty(value)? null : JSONObject.parseObject(value, clazz));
	}

	public void putList(String key, List<Object> list) {
		List<String> values = Lists.newArrayList();

		for (Object obj : list) {
			String value = JSON.toJSONString(obj);
			values.add(value);
		}

		getJedisCluster().rpush(key, values.toArray(new String[]{}));
	}

	public <T> List<T> queryList(String key, Class<T> clazz, long start, long end) {

		List<String> list = getJedisCluster().lrange(key, start, end);

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

		getJedisCluster().hmset(key, strMap);
	}

	public <T> Map<String, T> getMap(String key, Class<T> clazz, String... fields) {

		Map<String, T> resultMap = Maps.newHashMap();

		JedisCluster cluster = getJedisCluster();

		for (String field : fields) {
			String value = cluster.hget(key, field);

			T t = JSONObject.parseObject(value, clazz);

			resultMap.put(field, t);
		}

		return resultMap;
	}

	public <T> Map<String, T> getMap(String key, Class<T> clazz) {

		Map<String, T> resultMap = Maps.newHashMap();

		JedisCluster cluster = getJedisCluster();

		Set<String> fields = cluster.hkeys(key);

		return getMap(key, clazz, fields.toArray(new String[]{}));
	}

	public <T> List<T> popObject(String key, Class<T> clazz) {
		List<T> list = Lists.newArrayList();

		JedisCluster cluster = getJedisCluster();

		long length = cluster.llen(key);

		for (long i = 1; i < length; i++) {

			String value = cluster.lpop(key);

			T t = JSONObject.parseObject(value, clazz);

			list.add(t);
		}

		return list;
	}

	/**
	 * JedisCluster不支持事务,无法保证执行的每一个命令主键都在同一个节点
	 * liuhao
	 * 2017/12/8 7:43
	 *  * @param null
	 * @return
	 * @exception
	 */
	public Transaction getTransaction() throws NotSupportedException {
		throw new NotSupportedException("JedisCluster 不支持 事务操作");
	}

	// -----------------------------------------------------------------

	public long setAdd(String key, Object value) {
		return getJedisCluster().sadd(CommonUtils.parserObject(key), new byte[][] { CommonUtils.parserObject(value) })
				.longValue();
	}

	public <T> List<T> sorting(Class<T> klass, String key, SortingParams sorting) {
		List<byte[]> byteList = getJedisCluster().sort(CommonUtils.parserObject(key), sorting);
		List<T> result = new ArrayList<T>(byteList.size());
		for (byte[] data : byteList) {
			T e = JSONObject.parseObject(data, klass);
			result.add(e);
		}
		return result;
	}

	public void putObjectExpire(String key, Object value, int seconds) {
		byte[] k = CommonUtils.parserObject(key);
		byte[] v = CommonUtils.parserObject(value);
		getJedisCluster().setex(k, seconds, v);
	}

	public long sortedsetAdd(String key, Object value, double score) {
		Long success = getJedisCluster().zadd(CommonUtils.parserObject(key), score, CommonUtils.parserObject(value));
		return success == null ? 0L : success.longValue();
	}

	public long sortedsetRem(String key, Object value) {
		Long success = getJedisCluster().zrem(CommonUtils.parserObject(key), new byte[][] { CommonUtils.parserObject(value) });
		return success == null ? 0L : success.longValue();
	}

	public <E> List<E> sortedsetRange(String key, int start, int end, Class<E> klass) {
		Set<byte[]> set = getJedisCluster().zrange(CommonUtils.parserObject(key), start, end);
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
			return impl != null ? handleProxyApiMethod(impl, args) : handleJedisClusterMethod(method, args);
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

	private Object handleJedisClusterMethod(Method method, Object[] args) throws Throwable {
		return method.invoke(getJedisCluster(), args);
	}

	private static void init() {
		Method[] methods = RedisClientApi.class.getMethods();
		for (Method m : methods) {
			Method impl = getTargetMethod(RedisClusterClientProxy.class, m.getName(), m.getParameterTypes());
			proxyApiMethodMap.put(m.getName(), impl);
		}
	}

	public <T> List<T> lrangeObject(String key, long start, long end, Class<T> cls) {
		List<byte[]> list = getJedisCluster().lrange(CommonUtils.parserObject(key), start, end);
		List<T> retList = null;
		if (!CollectionUtil.isEmpty(list)) {
			retList = new ArrayList<T>(list.size());
			for (byte[] bytes : list) {
				T e = JSONObject.parseObject(bytes, cls);
				retList.add(e);
			}
		}
		return retList;
	}

	public <T> Map<String, T> hgetAllObject(String key, Class<T> cls) {
		byte[] key_bytes = CommonUtils.parserObject(key);
		Map<byte[], byte[]> dataMap = getJedisCluster().hgetAll(key_bytes);

		Map<String, T> retMap = new HashMap<String, T>();
		if (!CollectionUtil.isEmpty(dataMap)) {
			for (Map.Entry<byte[], byte[]> e : dataMap.entrySet()) {
				String key4Map = new String(e.getKey());
				T value4Map = (T) JSON.parse(e.getValue());
				retMap.put(key4Map, value4Map);
			}
		}
		return retMap;
	}

	@Override
	public List<?> checkAndSet(String paramString, Object paramObject) {
		try {
			throw new NotSupportedException();
		} catch (NotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<?> checkAndSetExpire(String paramString, Object paramObject, int paramInt) {
		try {
			throw new NotSupportedException();
		} catch (NotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	public JedisCluster getJedisCluster() {
		return location.getCluster();
	}

	static {
		init();
	}

	private static Method getTargetMethod(Class<?> clazz, String methodName, Class<?> [] params) {
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