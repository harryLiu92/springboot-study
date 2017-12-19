package com.liuhao.redis.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liuhao.common.utils.CollectionUtil;
import com.liuhao.common.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Transaction;

import javax.resource.NotSupportedException;

public abstract interface RedisClientApi {

	public abstract void close();

	public abstract void putObject(String key, Object value);

	public abstract <T> T getObject(String key, Class<T> clazz);

	public abstract void putList(String key, List<Object> list);

	public abstract <T> List<T> queryList(String key, Class<T> clazz, long start, long end);

	public abstract void putMap(String key, Map<String, Object> map);

	public abstract <T> Map<String, T> getMap(String key, Class<T> clazz, String... fields);

	public abstract <T> Map<String, T> getMap(String key, Class<T> clazz);

	public abstract <T> List<T> popObject(String key, Class<T> clazz);

	public abstract Transaction getTransaction() throws NotSupportedException;

	// -----------------------------------------------------------------

	public abstract long setAdd(String key, Object value);

	public abstract <T> List<T> sorting(Class<T> klass, String key, SortingParams sorting);

	public abstract List<?> checkAndSet(String key, Object value);

	public abstract List<?> checkAndSetExpire(String key, Object value, int seconds);

	public abstract void putObjectExpire(String key, Object value, int seconds);

	public abstract long sortedsetAdd(String key, Object value, double score);

	public abstract long sortedsetRem(String key, Object value);

	public abstract <E> List<E> sortedsetRange(String key, int start, int end, Class<E> klass);
}
