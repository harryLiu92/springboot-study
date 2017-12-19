package com.liuhao.mybatis.entity;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.liuhao.mybatis.sharding.holder.ShardingHolder;

public class Page<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<T> data;
	
	private long total;

	public Page(List<T> data) {
		this.data = data;
		this.total = ShardingHolder.totalCountHolder.get();
	}

	public Page(List<T> data, long total) {
		this.data = data;
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
