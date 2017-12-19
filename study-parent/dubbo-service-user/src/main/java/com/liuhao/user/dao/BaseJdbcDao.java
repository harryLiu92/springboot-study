package com.liuhao.user.dao;

import java.io.Serializable;
import java.util.ArrayList;


public interface BaseJdbcDao<T extends Serializable> {

	ArrayList<T> findEntity(String sql,Class<T> clazz);

	Object findObject(Class<T> clazz);

	// ��ɾ��
	int insert(String sql);

	int delete(String sql);

	int update(String sql);

	// ��������
	void createStatement();

	void addBatch(String sql);

	int executeBatch();

	String getSQL();
}
