package com.liuhao.redis.config;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.pool2.impl.AbandonedConfig;

import redis.clients.jedis.JedisPoolConfig;

@XmlRootElement(name = "redis")
public class RedisConfig implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name;
    private List<RedisNode> node;
    private String password;
    private int timeout;
    private int maxAttempts;
    private int maxAvtive;
    private int maxIdle;
    private int minIdle;
    private boolean blockWhenExhausted;
    private long maxWait;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean testWhileIdle;
    private long minEvictableIdleTimeMillis;
    private long timeBetweenEvictionRunsMillis;
    private int numTestsPerEvictionRun;
    private long softMinEvictableIdleTimeMillis = -1L;

    boolean logAbandoned;
    private boolean removeAbandonedOnBorrow;
    private boolean removeAbandonedOnMaintenance;
    private int removeAbandonedTimeout;
    private boolean useUsageTracking;

    private JedisPoolConfig jedisPoolConfig;

    private AbandonedConfig abandonedConfig;

    @XmlElement(name = "node")
    public List<RedisNode> getNode() {
		return node;
	}

	public void setNode(List<RedisNode> node) {
		this.node = node;
	}

	public boolean isLogAbandoned() {
		return logAbandoned;
	}

	public void setLogAbandoned(boolean logAbandoned) {
		this.logAbandoned = logAbandoned;
	}

	public boolean isRemoveAbandonedOnBorrow() {
		return removeAbandonedOnBorrow;
	}

	public void setRemoveAbandonedOnBorrow(boolean removeAbandonedOnBorrow) {
		this.removeAbandonedOnBorrow = removeAbandonedOnBorrow;
	}

	public boolean isRemoveAbandonedOnMaintenance() {
		return removeAbandonedOnMaintenance;
	}

	public void setRemoveAbandonedOnMaintenance(boolean removeAbandonedOnMaintenance) {
		this.removeAbandonedOnMaintenance = removeAbandonedOnMaintenance;
	}

	public int getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}

	public void setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	public boolean isUseUsageTracking() {
		return useUsageTracking;
	}

	public void setUseUsageTracking(boolean useUsageTracking) {
		this.useUsageTracking = useUsageTracking;
	}

	public AbandonedConfig getAbandonedConfig() {
		AbandonedConfig abandonedConfig = new AbandonedConfig();
		{
			abandonedConfig.setLogAbandoned(this.logAbandoned);
			abandonedConfig.setRemoveAbandonedOnBorrow(this.removeAbandonedOnBorrow);
			abandonedConfig.setRemoveAbandonedOnMaintenance(this.removeAbandonedOnMaintenance);
			abandonedConfig.setRemoveAbandonedTimeout(this.removeAbandonedTimeout);
			abandonedConfig.setUseUsageTracking(this.useUsageTracking);
		}

		this.abandonedConfig = abandonedConfig;
		return abandonedConfig;
	}

	public void setAbandonedConfig(AbandonedConfig abandonedConfig) {
		this.abandonedConfig = abandonedConfig;
	}

	public JedisPoolConfig getJedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		{
			jedisPoolConfig.setMaxIdle(this.maxIdle);
			jedisPoolConfig.setMinIdle(this.minIdle);
			jedisPoolConfig.setMaxWaitMillis(this.maxWait);
			jedisPoolConfig.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
			jedisPoolConfig.setNumTestsPerEvictionRun(this.numTestsPerEvictionRun);
			jedisPoolConfig.setTestOnBorrow(this.testOnBorrow);
			jedisPoolConfig.setTestOnReturn(this.testOnReturn);
			jedisPoolConfig.setTestWhileIdle(this.testWhileIdle);
			jedisPoolConfig.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
		}
		this.jedisPoolConfig = jedisPoolConfig;
        return jedisPoolConfig;
    }

    public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
        this.jedisPoolConfig = jedisPoolConfig;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public boolean isBlockWhenExhausted() {
        return blockWhenExhausted;
    }

    public void setBlockWhenExhausted(boolean blockWhenExhausted) {
        this.blockWhenExhausted = blockWhenExhausted;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {

        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public void setMaxAvtive(int maxAvtive) {
        this.maxAvtive = maxAvtive;
    }

    public int getMaxAvtive() {
        return maxAvtive;
    }

    public long getSoftMinEvictableIdleTimeMillis() {
        return softMinEvictableIdleTimeMillis;
    }

    public void setSoftMinEvictableIdleTimeMillis(long softMinEvictableIdleTimeMillis) {
        this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
    }

    public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}

	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
